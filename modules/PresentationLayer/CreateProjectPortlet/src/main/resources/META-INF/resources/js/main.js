function showAddNoteDialog() {
    var value;
    AUI().use('aui-io-request', function (A) {
        A.io.request('${testAjaxResourceUrl}', {
            method: 'POST',
            data: {
                "<portlet:namespace />projectName": document.getElementById("<portlet:namespace/>projectName").value
            },
            on: {
                success: function (data) {
                    var resp = this.get('responseData');
                    checkName(resp);
                    return data;
                }
            }
        });
    });

    function checkName(data) {
        var statusContent = '';
        var dataJson = JSON.parse(data);
        Object.keys(dataJson).forEach(function (key) {
            var glyphIconType;
            if (dataJson[key] === true) {
                glyphIconType = "glyphicon-ok";
            } else {
                glyphIconType = "glyphicon-remove";
            }
            statusContent += "<p>" + key + " : " + '<span class="glyphicon ' + glyphIconType +
                '"></span>' + "</p>";
            ;
        });

        YUI().use('aui-modal', function (Y) {
                var modal = new Y.Modal(
                    {
                        bodyContent: statusContent,
                        centered: true,
                        headerContent: '<h2>Summary of where project will be created</h2>',
                        modal: true,
                        render: '#modal',
                        width: 500,
                        zIndex: 5,
                    }
                ).render();
                modal.addToolbar(
                    [
                        {
                            label: '<liferay-ui:message key="Cancel"/>',
                            on: {
                                click: function () {
                                    modal.hide();
                                }
                            }
                        },
                        {
                            label: '<liferay-ui:message key="Create"/>',
                            on: {
                                click: function () {
                                    modal.hide();
                                    document.getElementById("<portlet:namespace/>fm").submit();
                                }
                            }
                        },
                    ]
                );
            }
        );
    }
}