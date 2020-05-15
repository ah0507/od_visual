$("#login").on("click",function () {
    var username = $("#username").val();
    var password = $("#password").val();
    var obj = {
        username: username,
        password:password
    };
    $.ajax({
        url: '/login',
        type: "POST",
        data: JSON.stringify(obj),
        contentType:"application/json",
        success: function (data) {
            if (data.code == 200) {
                location.href = "/index";
            }else{
                alert(data.msg)
            }
        }
    });
})

$('.clearBorder').on('click',function(){
    $(this).css('border',"1px solid transparent");
})