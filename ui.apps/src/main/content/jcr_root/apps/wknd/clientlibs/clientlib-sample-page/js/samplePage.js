console.log('ciao page');
/*document.querySelector('.cmp-helloworld__background').addEventListener('click', function (){
    document.body.style.backgroundColor= 'purple';
})*/
$(document).ready(function (){
    $('.cmp-helloworld__background').click(function (){
        $('body').css('background-color', 'purple');
    });
});