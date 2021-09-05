$(document).ready(
    function () {
        loadLogs()

        function loadLogs(){
            const paramsString = document.location.search;
            const searchParams = new URLSearchParams(paramsString);
            let amount = 0
            let o = 0
            setInterval(function() {
            $.ajax({
                type: 'GET',
                url: `${host}/users/logs?${searchParams}`,
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function (data) {
                       data.forEach(function (item, i) {
                           if (i+1 > amount) {
                               let action
                               let time = `${item.actionDay} ${item.actionMonth} at ${item.actionHour}:${item.actionMinute}`
                               let object = item.objectName
                               if (object === null) {
                                   action = `${item.action}`
                               } else action = `${item.action} of <u>${object}</u>`
                               fillAlert(time, item.subjectName, action)
                               amount = amount + 1
                           }
                    })
                }
            })
           }, 2000)
        }

        function fillAlert(time, subject, action) {
            $('#container').append(
                `<p><strong>${time}</strong> <u>${subject}</u> <mark>${action}</mark></p>`
            )
        }
    })
