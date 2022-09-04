var index = 0;
// 点击下一张
function next() {
    index++;
    if(index === 2) index = 0 ;
    clearInterval(timer);
    setStyle();
    run();
}
// 点击上一张
function prev() {
    index--;
    if(index === -1) index = 1;
    clearInterval(timer);
    setStyle();
    run();
}

let arr = [
"http://www.fjtydy.com/picture/client/index-banner1.jpg",
"http://www.fjtydy.com/picture/client/index-banner2.jpg",
];
var images = document.getElementById('container-img');
let indexes = document.getElementsByClassName('indexes')[0].children;
let timer = -1;

for (let i = 0; i < indexes.length; i++) {
    // 鼠标移入圆点,停止自动轮播并改变相应圆点样式
    indexes[i].onmouseover = function () {                
        index = i;
        clearInterval(timer);
        setStyle();
    };
    // 鼠标移出圆点,恢复自动轮播
    indexes[i].onmouseleave = function () {
        run();
    };
}
// 自动轮播
function run() {
    timer = setInterval(() => {
        index++;

        if(index === 2) index = 0;
        setStyle();
    }, 5000);
};
// 设置圆点样式同步对应图片
function setStyle() {
    images.src = arr[index];              
    let active = document.getElementsByClassName('active')[0];
    active.classList.remove('active');
    indexes[index].classList.add("active");
};
run();

var c=document.getElementById('title');
var d=document.getElementById('img');
var e=document.getElementById('article');
function moveLeft() {
	c.style.marginLeft='400px';
	d.style.marginLeft='350px';
}
function moveBack() {
	c.style.marginLeft='450px';
	d.style.marginLeft='400px';
}
function articleUp() {
	e.style.marginTop='180px';
}
function articleBack() {
	e.style.marginTop='200px';
}

$(function(){

    $.ajax({
        type: "get",
        url: "http://www.fjtydy.com/newIndex",
        dataType:"json",
        success:function(data) {
            $(".new-left").find("a").prop("href","http://www.fjtydy.com/"+data[0].pagePath)
            $(".new-left").find("a").eq(1).html(data[0].title)
            $(".new-left").find("img").prop("src","http://www.fjtydy.com/"+data[0].imgPath)
            var str=data[0].dates
            var newStr = str.replace(/-/g, ".");
            $(".new-right-top").find("div").eq(0).html(newStr)
            $(".new-right-top").find("a").eq(0).html(data[0].title).prop("href","http://www.fjtydy.com/"+data[0].pagePath)
            var texts=data[0].introduce
            var newtext=countNum(texts)
            $(".new-right-top").find("span").html(newtext) 
        }
    })

    $.ajax({
        type: "get",
        url: "http://www.fjtydy.com/newList",
        dataType:"json",
        success:function(data) {
            for(var i in data){
                $(".new-right-body").find("a").eq(i).html(data[i].title).prop("href","http://www.fjtydy.com/"+data[i].pagePath)
                $(".new-right-body").find("span").eq(i).html(data[i].dates)
            }
        }
    })

    $(".display").on("mouseover",function(){
        $(this).find("span").eq(1).css("background","#6495ED")
        $(this).find(".shadow,.word").css("display","block")
    })
    $(".display").on("mouseout",function(){
        $(this).find("span").eq(1).css("background","#BEBEBE")
        $(this).find(".shadow,.word").css("display","none")
    })
})

function countNum(e) {
    var t=[]
   if(e.length>80){
        t=e.slice(0,80)
        return t+"..."
   }else{
        return e
   }
  
}

function show1() {
   $("#dis1,#hid,#close").css("display","block")
}

function show2() {
  $("#dis2,#hid,#close").css("display","block")
}

function playVideo1() {
    $("#autoplay1,#hid,#close").css("display","block")
}
function playVideo2() {
    $("#autoplay2,#hid,#close").css("display","block")
}

function closePlay(){
    $("#autoplay1,#autoplay2,#dis1,#dis2,#hid,#close").css("display","none")
}
