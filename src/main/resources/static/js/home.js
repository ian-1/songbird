function changeTextButton() {
    var changBtn = document.getElementById("changeButton");
    if(changeBtn.value == "Click me!") {
        changeBtn.value = "You clicked me!";
        changBtn.classList.add("green");
        changBtn.classList.remove("red");
    } else {
        changeBtn.value = "Click me!";
        changBtn.classList.add("red");
        changBtn.classList.remove("green");
    }
}
