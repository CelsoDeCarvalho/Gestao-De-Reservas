package mz.com.sidratech.controller;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author celso
 */
public class Mascara {
    
    /**
     * 
     * @param textField  recebe um objecto do tipo textfield e o obriga a aceitar so numeros
     */

    public void apenasNumero(TextField textField) {

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String s = "";
            for (char c : newValue.toCharArray()) {
                if (((int) c >= 48 && (int) c <= 57 || (int) c == 46)) {
                    s += c;
                }
            }
            textField.setText(s);
        });
    }
}
