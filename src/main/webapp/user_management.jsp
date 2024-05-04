<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Quản lý người dùng</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/datatables.min.css">
</head>
<body>
    <div id="wrapper">
        <section class="container">
            <div class="table-container" style="margin: 50px 200px">
                <table id="table-user" class="display" data-order='[[0, "asc"]]'>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Email</th>
                            <th>Full Name</th>
                            <th>Gender</th>
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
        $('#table-user').DataTable({
            processing: true,
            serverSide: true,
            info: true,
            pageLength: 25,
            scrollY: 400,
            ajax: {
                url: 'user-management',
                type: 'POST',
                data: function(d) {
                    d.limit = d.length;
                    d.offset = d.start;
                },
                dataSrc: {
                    data: 'userList',
                    recordsTotal: 'recordsTotal',
                    recordsFiltered: 'recordsFiltered'
                }
            },
            columns: [
                { data: 'id' },
                { data: 'email' },
                { data: 'fullName' },
                { data: 'gender' }
            ]
        });
    })
</script>
</html>
