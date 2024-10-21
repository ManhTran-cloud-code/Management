$(document).ready(function() {
    $("#register_form").validate({
        rules: {
            username: "required",
            email: {
                required:true,
                email: true,
            },
            password: "required",
            re_password: {
                required:true,
                equalTo: "#password",
            }
        },
        messages: {
            username: "Vui long nhap ten cua ban",
            email: {
                required: "Nhap vao dia chi email cua ban",
                email:"Kong dung dn dang cua email"
            },
            password: "Vui long nhap vao mat khau cua ban",
            re_password: {
                required:"Vui long nhap xac nhan mat khau",
                equalTo:"Mat khau xac nhan phai giong moi mat khau tren"
            }
        },
        submitHandler: function(form,e) {
            e.preventDefault();
            let username = $("#username").val();
            let email= $("#email").val();
            let password = $("#password").val();
            let user = {"name":username,"email":email,"encryptPassword":password}
            $.ajax({
                url:'/api/user/save',
                method:"Post",
                data:JSON.stringify(user),
                contentType:"application/json",
                success:function (){
                    window.alert("Register Success");
                },
                error: function (){
                    window.alert("fail")
                }
            })
        }
    })
})