/**
 * Execute an asynchronous piece of JavaScript in the context of the currently selected frame or
 * window. Unlike executing {@link #executeScript(String, Object...) synchronous JavaScript},
 * scripts executed with this method must explicitly signal they are finished by invoking the
 * provided callback. This callback is always injected into the executed function as the last
 * argument.
 **/
var callback = arguments[arguments.length - 1];

function appendLib()
{
    console.log("appendLib");
    var head = document.getElementsByTagName('head')[0];
    var scriptElt = document.createElement('script');
    scriptElt.src = 'https://cdnjs.cloudflare.com/ajax/libs/javascript-canvas-to-blob/2.2.0/js/canvas-to-blob.min.js';
    var scriptElt0 = document.createElement('script');
    scriptElt0.src = 'https://github.com/niklasvh/html2canvas/releases/download/0.5.0-alpha1/html2canvas.js';
    head.appendChild(scriptElt0);
    head.appendChild(scriptElt);
}

//if (typeof html2canvas !== 'undefined') {
//    callback("html2canvas loaded, No need to inject")
//    console.log("a tutaj ???");
//}

var script = document.querySelector("script[src='https://github.com/niklasvh/html2canvas/releases/download/0.5.0-alpha1/html2canvas.js']");

if (script === null) {
    appendLib();
    callback("undefined html2canvas, libs injection");
}
callback("loaded html2canvas, No need to inject");








