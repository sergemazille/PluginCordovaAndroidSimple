package com.sergemazille.reorderstring;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReorderString extends CordovaPlugin {

    // Contriendra le message à retourner à l'application Cordova
    private String sortedMessage;
    private String receivedMessage;

    public boolean execute(String action, JSONArray messages, CallbackContext callback) throws JSONException {

        // Récupère le message à l'index 0 car il est le premier (et le seul) élément de l'objet JSONArray
        receivedMessage = messages.getString(0);

        // Créé un tableau de caractères à partir du message original
        char[] chars = receivedMessage.toCharArray();

        // En fonction de l'action demandée, on va soit trier dans l'ordre alphabétique soit dans l'ordre inverse :

        /* *** Tri par ordre alphabétique *** */
        if (action.equals("ordreAlphabetique")) {

            Arrays.sort(chars); // tri le tableau
            this.sortedMessage = new String(chars); // recrée un String à partir du tableau de caractères et l'assigne au message à retourner

        /* *** Tri dans l'ordre inverse *** */
        } else if (action.equals("ordreInverse")) {

            Arrays.sort(chars); // tri le tableau dans l'ordre alphabétique

            List<Character> charList = new ArrayList<Character>(); // Créé une 'List' d'objets Character vide

            // Pour chacun des caractères du tableau d'origine, on créé un Objet Character que l'on assigne à la 'List' d'objets Character
            for (char c : chars) {
                charList.add(c);
            }

            Collections.sort(charList, Collections.reverseOrder()); // Inverse l'ordre de la 'List'

            // On créé un StringBuilder dans lequel on passe un à un les caractères de la 'List' pour retrouver un String
            StringBuilder stringBuilder = new StringBuilder(charList.size());
            for (char c : charList) {
                stringBuilder.append(c);
            }

            // Et on finit par assigner le StringBuilder au message à renvoyer
            this.sortedMessage = stringBuilder.toString();


        /* *** Sinon, il y a une erreur *** */
        } else {
            // Si aucune des actions n'est appelée c'est qu'il y a une erreur quelque part, on envoie un message via le callback et on retourne false
            callback.error("Erreur : action non définie");
            return false;
        }

        // Envoie le message modifié à Cordova via l'objet CallbackContext fourni et termine la requête en retournant le booléen qui indique que tout s'est bien passé
        callback.success(this.sortedMessage);
        return true;
    }
}