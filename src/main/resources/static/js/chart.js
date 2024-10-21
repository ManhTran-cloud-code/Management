fetch('/api/analysis/chart')
    .then(response => response.json())
    .then(data => {
        // Chuẩn bị dữ liệu cho biểu đồ
        console.log( data)
        const labels = data.map(sale => sale.orderDate);  // Lấy tên sản phẩm làm label
        const salesData = data.map(sale => sale.totalMoney);    // Lấy số tiền bán hàng làm dữ liệu

        // Khởi tạo biểu đồ với dữ liệu
        const ctx = document.getElementById('salesChart').getContext('2d');
        const salesChart = new Chart(ctx, {
            type: 'line',  // Biểu đồ dạng cột
            data: {
                labels: labels,
                datasets: [{
                    label: 'Tong so tien',
                    data: salesData,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    })
