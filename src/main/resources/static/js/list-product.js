function loadAllProduct(){
    $.ajax({
        url:'/api/product/findAll',
        method:'GET',
        success: function (products){
            console.log(products);
             $("#table_body").empty();
            let status=1;
            $.each(products,function (i,product){
                 let inputTable = `<tr>
                                          <th scope="row">${status}</th>
                                          <td>${product.name}</td>
                                          <td>${product.quantity}</td>                
                                          <td>${product.price}</td>
                                          <td><a class="btn btn-danger deleteBtn" data-id="${product.id}">DELETE</a>
                                          <a class="btn btn-warning updateBtn" data-id="${product.id}">UPDATE</a></td>
                                          </tr>`
                $("#table_body").append(inputTable);
                status++;
            })

        }
    })
}

$(document).ready(function (){
    loadAllProduct();
})

$(document).on('click',".deleteBtn",function (){
        let id = $(this).data("id");
        $.ajax({
            url:'/api/product/delete/'+id,
            method:"DELETE",
            success:function (){
                window.alert("Delete Success!");
                loadAllProduct();

            },
            error: function (){
                window.alert("Delete Fail!");
            }
        })
})

$(document).on('click','.updateBtn',function(){
    let id = $(this).data("id");
    let url = '/product/'+id;
    $(this).attr("href",url);
})