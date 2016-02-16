module.exports = {
    order: function (action, message, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "ReorderString", action, [message]);
    }
};