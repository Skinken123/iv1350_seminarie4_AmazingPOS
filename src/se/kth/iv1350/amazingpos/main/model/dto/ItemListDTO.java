package se.kth.iv1350.amazingpos.main.model.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a data transfer object (dto) for the sale class,
 * used for sending information about an instance of the itemList in the sale class though packages.
 */
public class ItemListDTO {
    private List<ItemDTO> itemList;

    /**
     * Creates a new instance of a itemListDTO.
     * 
     * @param itemList The list of items in the sale.
     */
    public ItemListDTO(List<ItemDTO> itemList) {
        itemList = new ArrayList<>();
    }
}
