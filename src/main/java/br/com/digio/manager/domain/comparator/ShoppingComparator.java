package br.com.digio.manager.domain.comparator;

import br.com.digio.manager.domain.dto.ShoppingDTO;
import java.util.Comparator;

public class ShoppingComparator implements Comparator<ShoppingDTO> {

    @Override
    public int compare(ShoppingDTO s, ShoppingDTO s2) {
        if (s.getPrice() < s2.getPrice()) {
            return -1;
        } else if (s.getPrice() > s2.getPrice()) {
            return 1;
        }
        return 0;
    }
}
