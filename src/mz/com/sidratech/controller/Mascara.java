package mz.com.sidratech.controller;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author celso
 */
public class Mascara {

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
