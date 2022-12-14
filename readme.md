* lombok :
    @ToString
    @Data
    @Setter
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    public class Product implements Serializable{

        @Id
        @GeneratedValue(strategy =GenerationType.IDENTITY)
        private Long id;

        @Column(name = "product_name", length = 100)
        private String name;

        private String description;

        private double price;
    }
