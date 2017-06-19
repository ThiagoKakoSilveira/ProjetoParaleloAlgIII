/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

/**
 *
 * @author 631420067
 */
public class PegarBikesMenu {

    public static final int OP_RETIRAR_BIKE = 1;
    public static final int OP_FINALIZAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Retirar Bicicleta\n"
                + "0- Finalizar"
                + "\n--------------------------------------");
    }
}
