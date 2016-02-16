var app = {
    // Application Constructor
    initialize: function() {
        this.bindEvents();
    },

    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },

    onDeviceReady: function() {

        // Après l'évènement 'deviceready' on peut tester le plugin
        var sourceMessage = "Portez ce vieux whisky au juge blond qui fume";
        var action1 = "ordreAlphabetique";
        var action2 = "ordreInverse";

        // Callback appelé en cas de succès
        function successCallback(modifiedMessage){

            // On récupère une référence à la <div#msg> du fichier html pour lui passer les différents messages renvoyés par l'application native
            var displayMessage = document.getElementById("msg");

            // On créé ensuite un élément paragraphe 'p' à qui on passe le texte reçu et que l'on ajoute au DOM via le div dont on vient de récupérer la référence
            var messageParagraphe = document.createElement("p");
            messageParagraphe.innerHTML = modifiedMessage;
            displayMessage.appendChild(messageParagraphe);

            console.log(modifiedMessage);
        }

        // Callback appelé en cas d'échec
        function errorCallback(errorMessage){
            alert(errorMessage);
        }

        // tri des lettres par ordre alphabétique
        ReorderString.order(action1, sourceMessage, successCallback, errorCallback);

        // tri des lettres par ordre alphabétique inverse
        ReorderString.order(action2, sourceMessage, successCallback, errorCallback);

        // test d'une action qui n'existe pas dans la Class java (créé une alerte avec un message d'erreur renvoté par la Class java)
        ReorderString.order("actionNonExistente", sourceMessage, successCallback, errorCallback);
    }
};

app.initialize();