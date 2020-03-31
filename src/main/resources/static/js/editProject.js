$('.dropdown-toggle').dropdown();

$('#myEditProject').modal({
    keyboard: false
});


function getIMG(avatar)
{
    $('#avatar1').attr('value','icons/'+avatar);
    $('#img_edit_menu1').attr('src','img/icons/'+avatar);

}