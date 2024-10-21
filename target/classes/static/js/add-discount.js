$(document).ready(function (){
    $("#render_product").click(function (e){
        e.preventDefault();
        $.ajax({
            url:'/api/product/findAll',
            method:"GET",
            success:function(products){
                $.each(products,function (i,product){
                    let inputTable = `<tr>
                                          <th scope="row"><input type="checkbox" value="${product.id}"></th>
                                          <td>${product.name}</td>
                                          <td><img src="/images/product/${product.image}" alt="Image" style="width: 50px;height: 30px"></td>                
                                          <td>${product.quantity}</td>
                                          <td>${product.price}</td>
                                          </tr>`
                    $("#table_body").append(inputTable);
                })
                $("#product").DataTable();
            },
            error:function (){
                window.alert("Error")
            }
        })
    })
})

function getListProductId(){
    let listProduct = []
    $("#product tr").each(function (){
        let productId = $(this).find('input[type="checkbox"]:checked').val();
        if(!isNaN(productId)){
            listProduct.push(productId);
        }
    })
    return listProduct;
}
$(document).on('click','#check_product_id',function (e){
    e.preventDefault();
   if(getListProductId().length===0){
       window.alert("Ban nen chon 1 san pham bat ki de tiep tuc")
   }else{
       $('#myModal').modal('hide')
   }
   sessionStorage.setItem("list_product_id",getListProductId());

})
$(document).on('click','#delete_check_product_id',function (e){
     sessionStorage.removeItem("list_product_id");

})

$(document).ready(function (){
  $("#add_discount_btn").click(function (){
      $("#form_add_discount").validate({
         rules:{
             discount_name:"required",
             start_date: {
                 required:true,
                 greaterThanToday:true,
             },
             end_date:{
                 required: true,
                 greaterThan:"#start_date"
             },
             discount_type:{
                 valueNotEquals:'0'
             }
         },
          messages:{
             discount_name: "Hay cung cap ten cua chuong trinh!",
              start_date: {
                 required:"Hay cung cap ngay bat dau",
                  greaterThanToday: "Ngay bat dau phai lon hon hoac bang ngay hien tai"
              },
              end_date: {
                 required:"Hay cung cap ngay ket thuc!",
                  greaterThan: "Ngay ket thuc phai lon hon ngay duoc tao!"
              },
              discount_type: {
                 valueNotEquals: "Hay cung cap loai giam gia"
             }
          }
      })
      jQuery.validator.addMethod("greaterThan",
          function(value, element, params) {

              if (!/Invalid|NaN/.test(new Date(value))) {
                  return new Date(value) > new Date($(params).val());
              }

              return isNaN(value) && isNaN($(params).val())
                  || (Number(value) > Number($(params).val()));
          });

      $.validator.addMethod("greaterThanToday", function(value, element) {
              let inputDate = new Date(value);
              let today = new Date();
              today.setHours(0, 0, 0, 0); // Đặt giờ, phút, giây về 0 để so sánh chỉ ngày

              return inputDate > today; // Kiểm tra nếu ngày input lớn hơn hôm nay
          })
      $.validator.addMethod("valueNotEquals", function(value, element, arg){
          return arg !== value;
      })
})
})


function checkRadio(array){
 for(let i;i<array.length;i++){
     $("#product tr").each(function (){
         if($(this).find("input[type='checkbox']").val()===array[i]){
             $(this).find("input[type='checkbox']").prop("checked",true);
         }
     })
 }
}
$(document).ready(function (){
    let array = sessionStorage.getItem("list_product_id").split(",");
    $('input[type="checkbox"]').each(function() {
        if (array.includes(parseInt($(this).val()))) {
            $(this).prop('checked', true);
        }
    })
})