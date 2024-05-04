<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Quản lý đơn hàng</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/datatables.min.css">
</head>
<body>
    <div id="wrapper">
        <section class="container">
            <div class="table-container" style="margin: 50px 200px">
                <table id="table-order" class="display" data-order='[[0, "asc"]]'>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Người đặt</th>
                            <th>Sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Trạng thái</th>
                            <th>Ngày đặt</th>
                            <th>Ngày cập nhật</th>
                        </tr>
                    </thead>
                </table>
            </div>
        </section>
    </div>
</body>

<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="js/datatables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#table-order').DataTable({
            processing: true,
            serverSide: true,
            pageLength: 25,
            scrollY: 400,
            ajax: {
                url: 'order-management',
                type: 'POST',
                data: function(d) {
                    d.limit = d.length;
                    d.offset = d.start;
                },
                dataSrc: {
                    data: 'orderList'
                },
            },
            rowCallback: function(row, data) {
                $(row).attr('data-id', data.id);
            },
            columns: [
                {data: 'id'},
                {data: 'userId'},
                {data: 'productId'},
                {data: 'amount'},
                {
                    data: 'status',
                    render(data, type, row) {
                        let statusText = '';
                        switch (data) {
                            case 0:
                                statusText = 'Chờ xác nhận';
                                break;
                            case 1:
                                statusText = 'Đang đóng gói';
                                break;
                            case 2:
                                statusText = 'Chờ giao hàng';
                                break;
                            case 3:
                                statusText = 'Đã giao';
                                break;
                            case 4:
                                statusText = 'Đã hủy';
                                break;
                            case 5:
                                statusText = 'Trả hàng';
                                break;
                            default:
                                statusText = 'Không xác định';
                        }

                        let selectHtml = '<select class="status-select">';
                        const options = [
                            { value: 0, text: 'Chờ xác nhận' },
                            { value: 1, text: 'Đang đóng gói' },
                            { value: 2, text: 'Chờ giao hàng' },
                            { value: 3, text: 'Đã giao' },
                            { value: 4, text: 'Đã hủy' },
                            { value: 5, text: 'Trả hàng' }
                        ];

                        options.forEach(option => {
                            if (option.text === statusText) {
                                selectHtml += '<option value="' + option.value + '" selected>' + option.text + '</option>';
                            } else {
                                selectHtml += '<option value="' + option.value + '">' + option.text + '</option>';
                            }
                        });

                        selectHtml += '</select>';

                        return selectHtml;
                    }
                },
                {
                    data: 'dateCreate',
                    render: function (data, type, row) {
                        let date = new Date(data);
                        return date.toLocaleDateString();
                    }
                },
                {
                    data: 'dateUpdate',
                    render: function (data, type, row) {
                        let date = new Date(data);
                        return date.toLocaleDateString();
                    }
                },
                {
                    render(data, type, row) {
                        return '<button class="delete-btn">Xóa</button>'
                }}
            ]
        });

        $(document).on('change', '.status-select', function () {
            $.ajax({
                type: "POST",
                url: 'order-management',
                data: {
                    'edit': 'status',
                    'id': $(this).closest('tr').data('id'),
                    'newValue': $(this).val()
                }
            });
        });

        $(document).on('click', '.delete-btn', function () {
            let row = $(this).closest('tr');
            let id = row.data('id');
            row.remove();
        });
    })
</script>
</html>
