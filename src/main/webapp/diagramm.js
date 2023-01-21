const canvas = document.getElementById("MyCanvas");
console.log(canvas)
const ctx = canvas.getContext("2d");
const submitButton = document.getElementById("inputForm:submitButton");
const offset = 29;
let points = [];
let R = 1;
ctx.translate(150, 150);
ctx.scale(1,-1);
function drawSquare() {
    ctx.beginPath();
    ctx.fillStyle = "#152A3D";
    ctx.fillRect(-150, -150, 300, 300);
    // ctx.fillStyle = "#ce7b34"
    // ctx.moveTo(-150,-150);
    // ctx.lineTo(150,-150);
    // ctx.lineTo(150,150);
    // ctx.lineTo(-150,150);
    // ctx.lineTo(-150,-150);
    // ctx.stroke();
    ctx.closePath();
}
function drawAxes() {
    ctx.beginPath();
    ctx.moveTo(0, 0);

    const lineLen = 2;
    ctx.strokeStyle = "#646464";
    ctx.lineWidth = 2;
    ctx.moveTo(-150, 0);
    ctx.lineTo(150, 0);
    ctx.stroke();

    ctx.moveTo(0, -150);
    ctx.lineTo(0,150);
    ctx.stroke();

    ctx.fillStyle = "#e5e5e5";
    //ctx.fillStyle = "#152A3D";
    let start = -150 + 5

    for (let i = 0 ; i < 300; i += 29){
        ctx.moveTo(start + i, lineLen)
        ctx.lineTo(start  + i, -lineLen)
        ctx.stroke()
    }

    let k = -5;
    ctx.scale(1,-1);
    for (let i = 0 ; i < 300; i += 29){
        if (k!=0) {
            ctx.fillText(k.toString(), start + i, -5);
        }
        k++;
    }
    ctx.scale(1,-1);

    ctx.moveTo(0,150);
    ctx.lineTo(0, -150);
    ctx.stroke()
    start = -145;
    k = 5;
    for (let i = 0 ; i < 300; i += 29){
        ctx.moveTo(lineLen,start + i)
        ctx.lineTo(-lineLen, start + i)
        ctx.stroke()
    }

    ctx.scale(1,-1);
    for (let i = 0 ; i < 300; i += 29){
        if (k!=0) {
            ctx.fillText(k.toString(), 5, start + i);
        }
        k--;
    }
    ctx.scale(1,-1);

    ctx.fillStyle = "#e5e5e5"
    ctx.moveTo(150,0);
    ctx.lineTo(145,5);
    ctx.stroke()
    ctx.moveTo(150,0);
    ctx.lineTo(145,-5);
    ctx.stroke();
    ctx.fillText("X",143,-15);

    ctx.moveTo(0,150);
    ctx.lineTo(5,145);
    ctx.stroke()
    ctx.moveTo(0,150);
    ctx.lineTo(-5,145);
    ctx.stroke();
    ctx.scale(1,-1);
    ctx.fillText("y",-15,-143);
    ctx.scale(1,-1);
    ctx.closePath();
}
function drawFigures(r){
    ctx.beginPath();
    ctx.fillStyle = "#437FB1";

    moveTo(0,0);
    ctx.moveTo(0,0);
    ctx.lineTo(-r*offset,0);
    ctx.lineTo(-r*offset,-r*offset/2);
    ctx.lineTo(0,-r*offset/2);
    ctx.lineTo(0,0);
    ctx.fill();

    ctx.moveTo(0,0);
    ctx.lineTo(r/2*offset,0);
    ctx.lineTo(0,r*offset);
    ctx.lineTo(0,0);
    ctx.fill();

    ctx.arc(0,0,r/2*offset,Math.PI/2,Math.PI);
    ctx.fill();
    ctx.closePath();
}
function Point(x,y,color){
    this.x = x;
    this.y = y;
    this.color = color;
}
function drawPoint(point){
    //console.log(point);
    ctx.beginPath();
    ctx.fillStyle = point.color;
    ctx.arc(point.x*offset,point.y*offset,3,0,2*Math.PI);
    ctx.fill();
    ctx.closePath();
}
function drawPoints(){
    for (let i = 0;i<points.length;i++){
        drawPoint(points[i]);
    }
}
function redraw(r){
    console.log("redraw");
    parseTable();
    drawSquare();
    drawFigures(r);
    drawAxes();
    drawPoints();
}
// function drawFirst(){
//     points = [];
//     let lines = document.getElementById("formTable:mainTable_body").getElementsByTagName("tr");
//     for (let i = 0;i<lines.length;i++){
//         let elements = lines[i].getElementsByTagName("td");
//         let color = "#525252";
//         // if (elements[3].innerText==="true") color = "#58ff00";
//         // else color = "#ff0000";
//         let point = new Point(elements[0].innerText,elements[1].innerText,color);
//         points.push(point);
//         drawPoint(point);
//     }
// }
function parseTable(){
    points = [];
    let lines = document.getElementById("formTable:mainTable_body").getElementsByTagName("tr");
    if(lines.item(0).className === "ui-widget-content ui-datatable-empty-message"){
        return
    }
    for (let i = 0;i<lines.length;i++){
        let elements = lines[i].getElementsByTagName("td");
        let color = "#525252";
        if (elements[3].innerText==="true") color = "#58ff00";
        else color = "#ff0000";
        let point = new Point(elements[0].innerText,elements[1].innerText,color);
        points.push(point);
        //drawPoint(point);
    }
    console.log(points)
}
function clickEvent(event){
    //let x = event.offsetX - 150;
    //let y = -(event.offsetY - 150);
    let x = event.clientX-450;
    let y = -(event.clientY-200);
    x /= offset;
    x = x.toFixed(2);
    y /= offset;
    y = y.toFixed(2);
    console.log("x = " + x);
    console.log("y = " + y);

    if (x < -5 || x > 5){
        //alert("invalid x parameter")
        document.getElementById("invalidValue").innerText = "invalid x parameter"
        console.log("invalid x parameter")
        return;
    }
    if (y < -3 || y > 5) {
        //alert("invalid y parameter")
        document.getElementById("invalidValue").innerText = "invalid y parameter"
        console.log("invalid y parameter")
        return;
    }
    document.getElementById("invalidValue").innerText = " "

    document.getElementById("inputForm:inputY").value = y;
    //document.getElementById("inputForm:sliderX").
    //document.getElementById("inputForm:sliderX_hidden").value = x;
    document.getElementById("inputForm:inputX").value = x;


    if (submitButton.disabled){
        submitButton.disabled = false;
        submitButton.click()
        submitButton.disabled = true;
    }
    else {
        submitButton.click()
    }
    R = document.getElementById("inputForm:inputR").value;
    console.log("R = " + R)
    switch (R){
        case "1": document.getElementById("inputForm:setR1").click();
            break;
        case "2": document.getElementById("inputForm:setR2").click();
            break;
        case "3": document.getElementById("inputForm:setR3").click();
            console.log("Laba xyinia")
            break;
        case "4": document.getElementById("inputForm:setR4").click();
            break;
        case "5": document.getElementById("inputForm:setR5").click();
            break;
    }
}


window.onload = function (){
    //drawFirst();
    document.getElementById("MyCanvas").addEventListener("click",function (event){
        console.log(event);
        clickEvent(event);
    })
};

function sleep(milliseconds) {
    const date = Date.now();
    let currentDate = null;
    do {
        currentDate = Date.now();
    } while (currentDate - date < milliseconds);
}

function drawPointsByTime(){
    parseTable()
    drawPoints()
    console.log("Нарисовал")
}

setInterval(function (){
    drawPointsByTime()
},200)


//document.getElementById("inputForm:submitButton").addEventListener("click",drawPointsByTime);
redraw(5);

