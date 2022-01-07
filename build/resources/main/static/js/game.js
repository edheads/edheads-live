//The openwindow function take a URL as a parameter and opens it in a new pop-up window
function openwindow(url) {
    newwindow=window.open(url,'name','height=100%,width=auto');
    if (window.focus) {newwindow.focus()}
    return false;
}
