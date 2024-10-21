function loadCart(){
    $.ajax({
        url:"/api/cart/getAll",
        method:"GET",
        success:function (carts){
            $(".table_content").empty();
            $(".text-muted").text(carts.length);
            $.each(carts,function (i,cart){
                let input = ` <tr>
                                        <th scope="row"><input type="checkbox" class="checkbox" name="listId" value="${cart.cartId}" ></th>
                                        <td>${cart.product.name}s</td>
                                        <td><img src="/images/product/${cart.product.image}" alt="" style="width: 10rem;height: 12rem"></td>
                                        <td><input type="number" class="quantity" name="quantity" value="${cart.quantity}">
                                        </td>
                                        
                                        <td class="price">${cart.product.price}</td>
                                        <td class="money">${cart.product.price * cart.quantity}</td>
                                        <td><a class="deleteBtn btn btn-danger">Delete</a></td>
                                    </tr>`
                $(".table_content").append(input);
            })
        }
    })
}


$(document).ready(function (){
    loadCart();
})

$(document).on('click',".deleteBtn",function (){
    let cartId = $(this).closest("tr").find('input[type="checkbox"]').val();
    $.ajax({
        url:"api/cart/delete/"+cartId,
        method:"GET",
        success: function (){
            window.alert("Delete Success!");
            loadCart()
        }
    })
})

$(document).on('change','.quantity',function (){
    let quantity = $(this).val();
    let price = $(this).closest("tr").find(".price").text();
    let money = quantity*price;
    $(this).closest("tr").find(".money").text(money);
})

$(document).ready(function (){

})
let totalMoney=0;
$(document).on('change','input[type="checkbox"]',function (){
    let numberOfCheckbox = $('input[type="checkbox"]:checked').length;
    $("#numberCheckBox").text(numberOfCheckbox);
    let price = $(this).closest("tr").find(".price").text();
    let quantity = $(this).closest("tr").find(".quantity").val();
    let money = parseInt(price)*quantity;
    if($(this).is(":checked")){
        $(this).closest("tr").find(".quantity").prop('disabled',true);
        totalMoney+=money;
    }else{
        $(this).closest("tr").find(".quantity").prop('disabled',false);
        totalMoney-=money;
    }
$("#totalMoney").text(totalMoney);
})

$(document).on('click','#buynowbtn',function (e){
    e.preventDefault();
    if($('input[type="checkbox"]:checked').length===0){
        window.alert("You need choose one product!")
    }
    let totalMoney = $("#totalMoney").text();
    let formData = new FormData();
    formData.append("totalMoney",totalMoney);
    let listCartId = [];

    $(".table_content tr").each(function (){
        let cardId = $(this).find(".checkbox").val();
        listCartId.push(cardId);
        console.log("hello")
    })
    formData.append("card_id",listCartId);
    console.log(formData.get("card_id"));
    $.ajax({
        url:"/api/order/add",
        method:"POST",
        contentType:false,
        processData:false,
        data:formData,
        success: function (){

            window.alert("Success");
            loadCart();
        },
        error: function (xhr){
            window.alert(xhr.responseText);
        }
    })

})

