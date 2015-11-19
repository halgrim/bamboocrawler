/**
 * Created by i323728 on 11/2/15.
 */

var callback = arguments[arguments.length - 1];
//Watch out for script arguments object scope!
var webElement = arguments[0];
var filePath = arguments[1];

//wait until tml2canvas is loaded
var checkExist = setInterval(function () {
    if (typeof html2canvas !== 'undefined') {
        console.log("loaded!");
        // clear Interval
        clearInterval(checkExist);

        //this is the core of this file
        html2canvas(webElement, {
            onrendered: function (canvas) {
                canvas.toBlob(function (blob) {
                    var a = document.createElement("a");
                    document.body.appendChild(a);
                    a.style = "display: none";
                    a.href = window.URL.createObjectURL(blob);
                    a.download = filePath;
                    a.click();
                    document.body.removeChild(a);
                });
            }
        });

        //every JSExecutor async scrip has to have callback function to execute
        callback();

    } else {
        console.log("undefined!");
    }

}, 500);


