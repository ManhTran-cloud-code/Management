function loadOrderByStatus(status){
    $.ajax({
        url: "/api/order/status/"+status,
        method:"GET",
        success: function (orders){
            let stt = 1;
            $("#table-content").empty();
            $.each(orders,function (i,order){
                let input = `<tr>
                                          <th scope="row">${stt}</th>
                                          <td>${order.orderDate}</td>
                                          <td>${order.totalMoney}</td>                
                                          <td class="status">${convertStatusToString(order.orderStatus)}</td>
                                          <td><a href="${order.orderId}" class="modal_detail_link" data-toggle="modal" data-target=".modal_detail">Detail</a></td>
                                          </tr>`
                $("#table-content").append(input);
                stt++;
            })
        }
    })
}

function convertStatusToString(status){
    if(status===1){
        return "Chua xac nhan";
    }
    if(status===2){
        return "Da xac nhan"
    }
    if(status===3){
        return "Dang van chuyen";
    }
    if(status===4){
        return "Da giao xong";
    }
}

function convertStatusToInt(string){
    if(string==="Chua xac nhan"){
        return 1;
    }
    if(string==="Da xac nhan"){
        return 2;
    }
    if(string==="Dang van chuyen"){
        return 3;
    }
    if(string==="Da giao xong"){
        return 4;
    }
}

$(document).ready(function (){
   loadOrderByStatus(1)
})

$(document).on('click','.modal_detail_link',function (e){
     e.preventDefault();
     let orderId = $(this).attr("href");
     let orderStatus = $(this).closest("tr").find(".status").text();
     $.ajax({
           url:'/api/order/detail/'+orderId,
         method: 'GET',
         success: function (orderItems){
               let status = 1;
               $("#table-detail-content").empty();
               $.each(orderItems,function (i,orderItem){
                   let input = `<tr>
                                          <th scope="row">${status}</th>
                                          <td>${orderItem.product.name}</td>
                                          <td><img src="/images/product/${orderItem.product.image}" alt="" style="width: 50px;height: 40px"></td>                
                                          <td>${orderItem.quantity}</td>
                                          <td>${orderItem.quantity*orderItem.product.price}</td>                 
                                          </tr>`
                   $("#table-detail-content").append(input);
                   status++;
               })

             //them hanh dong vao
             let render_action = `<div class="text-center"><a href="${orderId}" class="btn btn-danger mb-2" id="change_status_button">${orderStatus}</a></div> 
                                         <hr>
                                         <div class="ml-2">
                                           <div>Note: If you click on button you will change status of order</div>
                                           <div> ---> If Order status is 'Chua xac nhan' after click is 'Da xac nhan'</div>
                                           <div> ---> If Order status is 'Da xac nhan' after click is 'Dang giao hang'</div>
                                           <div> ---> If Order status is 'Dang giao hang' after click is 'Giao Hang thanh cong'</div>
                                         </div>`
             $("#render_status").html(render_action);
         }
     })
})

$(document).on('click','#change_status_button',function (e){
    e.preventDefault();
    $("#change_status_button").attr("disabled","disabled");
  let orderId = $(this).attr("href");
  let status = convertStatusToInt($(this).text());
  $.ajax({
      url:"/api/order/updateStatus/"+status +"/" + orderId,
      method:"GET",
      success:function(){
          window.alert("Update Success!");
          loadOrderByStatus(status);
      },
      error: function (){
          window.alert("Update Fail");
      }
  })
})
$(document).ready(function (){
    $("#action1").click(function (e){
        e.preventDefault();
        $("#table-content").empty();
        loadOrderByStatus(1);
    })
})

$(document).on('click','#action2',function (e){
    e.preventDefault();
    loadOrderByStatus(2);
})

$(document).ready(function (){
    $("#action3").click(function (e){
        e.preventDefault();
        $("#table-content").empty();
        loadOrderByStatus(3);
    })
})
$(document).ready(function (){
    $("#action4").click(function (e){
        e.preventDefault();
        $("#table-content").empty();
        loadOrderByStatus(4);
    })
})