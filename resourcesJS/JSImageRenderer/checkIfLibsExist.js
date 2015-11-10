//i tyle wystarczy, bez definiowania callbacka? ? to musi w środku być funkcja anonimowa

var callback = arguments[arguments.length - 1];
if (typeof html2canvas !== 'undefined') {
    //every JSExecutor async scrip has to have callback function to execute
    callback('loaded');

} else {
    //every JSExecutor async scrip has to have callback function to execute
    callback('undefined');
}

