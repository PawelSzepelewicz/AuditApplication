$(document).ready(
    function () {
        getUsersList()

        function addRow(number, username, id) {
            $("#tbody").append(
                `<tr>
                   <th scope="row">${number}</th>
                   <td>${username}</td>
                   <td><a href="/logs?id=${id}">logs</a></td>
                 </tr>
                `
            )
        }

        function getUsersList() {
            $.ajax({
                type: 'GET',
                url: `${host}/users`,
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    data.forEach(function (item, i) {
                        addRow(i+1, item.username, item.id)
                    })
                }
            })
        }
    })
