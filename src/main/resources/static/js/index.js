$(document).ready(function (){
    $(".product_display").empty();
    $.ajax({
        url:"/api/product/findAll",
        method:"GET",
        success:function (products){
            $.each(products,function (i,product){
                let input = `<div class="col-4">
                                    <div class="card mt-2" style="width: 18rem">
                                    <img class="card-img-top" src="/images/product/${product.image}" alt="Card image cap" style="height: 20rem">
                                    <div class="card-body">
                                    <h5 class="card-title">${product.name}</h5>
                                    <p class="card-text">Hello. This is content!</p>
                                    <div>Price: ${product.price} VND</div>
                                    <a href="/product/${product.id}/${product.name}" class="btn btn-primary">Buy Now</a>
                                    </div>
                                    </div>
                                    </div>`
                $(".product_display").append(input);
            })
        }
    })
})

$(document).ready(function (){
    $.ajax({
        url:"/api/category/getAll",
        method:"GET",
        success: function (categories){
             $.each(categories,function (i,category){
                 let input = `<a href="${category.id}"  class="list-group-item list-group-item-action py-2 ripple active link_category">
                                     <span> ${category.name}</span></a>`
                 $("#navbar_category").append(input);
             })
        }
    })
})

function loadProductByCategory(categoryId){
    $.ajax({
        url:"/api/product/loadByCategoryId/" + categoryId,
        method:"GET",
        success:function (products){
            $.each(products,function (i,product){
                let input = `<div class="col-4">
                                    <div class="card mt-2" style="width: 18rem;">
                                    <img class="card-img-top" src="/images/product/${product.image}" style="height: 20rem" alt="Card image cap">
                                    <div class="card-body">
                                    <h5 class="card-title">${product.name}</h5>
                                    <p class="card-text">Hello. This is content!</p>
                                    <div>Price: ${product.price} VND</div>
                                    <a href="/product/${product.id}/${product.name}" class="btn btn-primary">Buy Now</a>
                                    </div>
                                    </div>
                                    </div>`
               $(".product_display").append(input);
            })
        }
    })
}


$(document).on('click','.list-group-item',function (e){
    e.preventDefault();
    $(".product_display").empty();
    let categoryId = $(this).attr("href");
    loadProductByCategory(categoryId);
})
