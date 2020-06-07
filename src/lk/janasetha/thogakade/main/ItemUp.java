package lk.janasetha.thogakade.main;


public class ItemUp {

    public static void main(String[] args) {

/*        StockBO stockBO = (StockBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);

        BatchDTO batchDTO = new BatchDTO("Uniliever", new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), "ACT");
        List<BatchDetailDTO> itemDetailList = new ArrayList<>();


        ItemDTO sunlight = new ItemDTO("Liftbouy Small Pack","ACT",1,1,null);
        BatchDetailDTO bd1 = new BatchDetailDTO(sunlight, 1000, 1000, 10.0, 7.0, 9.0, Date.valueOf("2020-01-01"), Date.valueOf("2022-01-01"));

        ItemDTO sunsilk = new ItemDTO("Sunsilk Small Pack", "ACT", 1, 1, null);
        BatchDetailDTO bd2 = new BatchDetailDTO(sunsilk, 500, 500, 8.0, 7.0, 8.0, Date.valueOf("2020-01-01"), Date.valueOf("2022-01-01"));


        itemDetailList.add(bd1);
        itemDetailList.add(bd2);

        try {

            System.out.println(stockBO.addNewStock(new CompleteStockDTO(batchDTO, itemDetailList)));

        } catch (Exception e) {
            e.printStackTrace();
        }*/

/*
        StockBO stockBO = (StockBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);

        try {
            List<CompleteStockDTO> allStockDetails = stockBO.getAllStockDetails();

            allStockDetails.forEach(stockDTO -> {
                System.out.println(stockDTO.getBatch());
                System.out.println();

                List<BatchDetailDTO> batchDetail = stockDTO.getBatchDetail();
                batchDetail.forEach(batchDetailDTO -> {
                    System.out.println(batchDetailDTO);
                    System.out.println(batchDetailDTO.getItem());
                    System.out.println();
                });

            });



        } catch (Exception e) {
            e.printStackTrace();
        }
*/


    }
}
