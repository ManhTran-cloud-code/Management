
$(document).ready(function (){
    loadCategory();
})

$(document).ready(function (){
    $("#add-category-btn").click(function(e){
        e.preventDefault();
        let categoryId = $("#category-id").val();
        let categoryName = $("#category-name").val();
        let category = {id:categoryId,name:categoryName}
        $.ajax({
            url:"/api/category/add",
            method:"POST",
            contentType:"application/json",
            data: JSON.stringify(category),
            success: function (){
                window.alert("Add Success!");
                loadCategory();
            },
            error:function (){
                window.alert("Category already exists");
            }
        })
    })
})

function loadCategory(){
    $(document).ready(function (){
        $.ajax({
            url: "/api/category/getAll",
            method: "GET",
            success: function (categories){
                let categoryTableBody = $("#body_table");
                categoryTableBody.empty();
                let status = 1;
                $.each(categories,function (i,category){
                    let row = `<tr>
                                     <td>${status}</td>
                                    <td>${category.id}</td>
                                    <td>${category.name}</td>
                                    <td>
                                        <button class="btn btn-danger editBtn" data-toggle="modal" data-target="#update-modal" data-id="${category.id}">Edit</button>
                                        <button class="btn btn-warning deleteBtn" data-id="${category.id}">Delete</button>
                                    </td>
                                </tr>`
                    $("#body_table").append(row);
                    status++;
                })
            }
        })
    })
}

$(document).on('click','.deleteBtn',function () {
    let id = $(this).data("id");
    $.ajax({
        url: 'api/category/delete/' + id,
        method: "DELETE",
        success: function () {
            loadCategory();
            window.alert("Delete Success!")
        }
    })
})

$(document).on('click','.editBtn',function (){
    let id = $(this).data("id");
    $.ajax({
        url:"api/category/"+id,
        method:"GET",
        success: function (category){
            let input = `<input type="hidden" id="id-category" value="${category.id}">
                                <div class="form-group">
                                <label for="name">Password</label>
                                <input type="text" class="form-control" id="name-category" value="${category.name}">       
                                </div>`
            $(".modal-body").html(input)
        }
    })
})

$(document).ready(function (){
    $("#submitBtn").click(function (e){
        e.preventDefault();
        let id = $("#id-category").val();
        let name = $("#name-category").val();
        let category = {"id":id,"name":name}
        $.ajax({
            url:"/api/category/update",
            method:"PUT",
            data: JSON.stringify(category),
            contentType:"application/json",
            success : function (){
                window.alert("Update Success!")
                loadCategory();
            },
            error:function (){
                window.alert("Update Fail!");
            }
        })
    })
})

/*
$(document).ready( function () {
    $('#category-table').DataTable();
} );*/
