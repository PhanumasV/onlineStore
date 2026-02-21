package onlineStore;

import behavioralObserverPattern.Order;
import behavioralObserverPattern.OrderHistory;
import behavioralStrategyPattern.CategorySearchStrategy;
import behavioralStrategyPattern.NameSearchStrategy;
import behavioralStrategyPattern.SearchStrategy;
import creational.ClothingFactory;
import creational.ElectronicsFactory;
import creational.GroceryFactory;
import creational.ProductFactory;
import creational.SingletonStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import models.Clothing;
import models.Electronics;
import models.Grocery;
import models.Product;
import models.ProductReview;
import services.Payment;
import services.ShoppingCart;
import structuralCompositePattern.ProductComposite;

/**
 * The {@code OnlineStoreSystem} class is the main entry point for an online store simulation.
 * <p>
 * It includes product management, user login, shopping cart operations, order history tracking,
 * and payment processing. The store supports various product categories such as groceries,
 * electronics, and clothing, with the ability to search and apply discounts.
 * </p>
 */
public class OnlineStoreSystem {

    /**
     * Scanner for user input.
     */
    static Scanner scanner = new Scanner(System.in);

    /**
     * A map to store usernames and passwords for user login.
     */
    static HashMap<String, String> userMap = new HashMap<>();

    /**
     * Tracks whether the user is currently logged in.
     */
    static boolean isLoggedIn = false;

    /**
     * Factory instance for creating grocery products.
     */
    static ProductFactory groceryFactory = new GroceryFactory();

    /**
     * Factory instance for creating electronics products.
     */
    static ProductFactory electronicsFactory = new ElectronicsFactory();

    /**
     * Factory instance for creating clothing products.
     */
    static ProductFactory clothingFactory = new ClothingFactory();

    /**
     * Singleton instance of the store.
     */
    static SingletonStore store = SingletonStore.getInstance();

    /**
     * Stores the username of the currently logged-in user.
     */
    static String currentUser = null;

    /**
     * Tracks the order history for the user.
     */
    private static OrderHistory orderHistory = new OrderHistory();

    /**
     * List of grocery products available in the store.
     */
    static List<Product> groceries = List.of(
        new Grocery("Apple", 1.00),
        new Grocery("Banana", 0.50),
        new Grocery("Orange", 1.20),
        new Grocery("Milk", 2.50),
        new Grocery("Chocolate", 1.80),
        new Grocery("Cheese", 3.00),
        new Grocery("Eggs", 2.00),
        new Grocery("Butter", 1.50),
        new Grocery("Tomato", 0.70),
        new Grocery("Potato", 0.40)
    );

    /**
     * List of electronics products available in the store.
     */
    static List<Product> electronics = List.of(
        new Electronics("Laptop", 600.00),
        new Electronics("Smartphone", 350.00),
        new Electronics("Headphones", 50.00),
        new Electronics("Tablet", 200.00),
        new Electronics("Smartwatch", 150.00),
        new Electronics("Playstation 5", 400.00),
        new Electronics("TV", 500.00),
        new Electronics("Bubu & Dudu: The Video Game", 79.00),
        new Electronics("Bluetooth Speaker", 70.00),
        new Electronics("Charger", 25.00)
    );

    /**
     * List of clothing products available in the store.
     */
    static List<Product> clothing = List.of(
        new Clothing("Jacket", 80.00),
        new Clothing("Shirt", 40.00),
        new Clothing("Jeans", 60.00),
        new Clothing("Sweater", 50.00),
        new Clothing("T-shirt", 20.00),
        new Clothing("Shorts", 25.00),
        new Clothing("Dress", 70.00),
        new Clothing("Coat", 90.00),
        new Clothing("Socks", 10.00),
        new Clothing("Scarf", 15.00)
    );

    /**
     * A combined list of all products available in the store.
     */
    static List<Product> allProducts = new ArrayList<>();

    /**
     * The shopping cart for the user.
     */
    static ShoppingCart cart = new ShoppingCart();

/**
     * The main method serves as the entry point for the Online Store System.
     * <p>
     * It initializes the store with predefined products, sets up user accounts, and displays the main menu,
     * allowing users to interact with the system.
     * </p>
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // To add initial products to the store
        SingletonStore store = SingletonStore.getInstance();
        store.addProducts(groceries);
        store.addProducts(electronics);
        store.addProducts(clothing);

        // Add predefined users
        userMap.put("Aysha", "123456");
        userMap.put("Shane", "123456");
        userMap.put("Christine", "123456");
        userMap.put("Ju", "123456");

        // Adding Admin user
        userMap.put("Admin", "Admin123");

        System.out.println("WELCOME TO THE ONLINE STORE SYSTEM!");

        // Main menu loop
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");

            int choice = -1;
            boolean validInput = false;

            // Handle user input for menu options
            while (!validInput) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            // Process user choice
            switch (choice) {
                case 1 -> userLogin();
                case 2 -> searchProducts();
                case 3 -> addProductToYourCart();
                case 4 -> removeProductFromYourCart();
                case 5 -> viewCart();
                case 6 -> proceedToPayment();
                case 7 -> orderHistory.displayOrderHistory();
                case 8 -> reviewProduct();
                case 9 -> reviews();
                case 10 -> addNewProduct();
                case 11 -> manageCompositeProducts();
                case 12 -> applyDiscountToProduct();
                case 13 -> signOut();
                case 0 -> exit = true;
                default -> System.out.println("Invalid choice. Please select an option from the menu.");
            }
        }
        System.out.println("Thank you for using the Online Store System. Goodbye!");
    }

    /**
     * Displays the main menu options for the Online Store System.
     * <p>
     * The menu includes options for user login, product search, cart management,
     * payment processing, and administrative functions.
     * </p>
     */
    public static void displayMenu() {
        System.out.println("""
                ----------------------------Menu-----------------------------
                1. User login
                2. Search product
                3. Add product to your cart
                4. Remove product from your cart
                5. View cart
                6. Proceed to payment
                7. View Order History
                8. Review product
                9. Reviews
                10. Add a new product (Admin)
                11. Manage Composite Products (Admin)
                12. Apply a discount to a product (Admin)
                13. Sign out
                0. Exit
                -----------------------------------------------------------------------
                """);
    }

    /**
     * Checks if the currently logged-in user is an admin.
     * <p>
     * This method verifies if the user is logged in and their username matches "Admin".
     * </p>
     *
     * @return {@code true} if the logged-in user is an admin, otherwise {@code false}.
     */
    public static boolean isAdmin() {
        return isLoggedIn && "Admin".equalsIgnoreCase(currentUser);
    }

    /**
     * Handles the user login process.
     * <p>
     * This method prompts the user for their username and password and checks the credentials
     * against the predefined user map. If the credentials are valid, the user is logged in.
     * </p>
     */
    public static void userLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Validate credentials
        if (userMap.containsKey(username) && userMap.get(username).equals(password)) {
            isLoggedIn = true;
            currentUser = username; // Track the logged-in user
            System.out.println("Login successful! Welcome, " + username + "!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    
    /**
    * Allows users to search for products in the store.
    * <p>
    * Users can search for products by category or by name. The method prompts the user to select
    * a search option, retrieves the necessary input for the chosen search strategy, and displays
    * the search results.
    * </p>
    * <p>
    * This method requires the user to be logged in. If the user is not logged in, it prompts them to log in first.
    * </p>
    */

    // To search for products
    public static void searchProducts() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
           return;
        }

        // Prompt user to select search type
        System.out.println("Search products:");
        System.out.println("1. Search by category");
        System.out.println("2. Search by name");

        int choice = -1; // Initial state for user's choice
        boolean validInput = false;

        // Validate the user's input for search type
        while (!validInput) {
            try {
                System.out.print("Enter your choice (1 or 2): ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) {
                    validInput = true;
                } else {
                    System.out.println("Invalid. Please select 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid. Please enter a number (1 or 2).");
            }
        }

        // Retrieve all products from the SingletonStore
        List<Product> products = SingletonStore.getInstance().getProducts();
        SearchStrategy strategy; // Defines the search strategy
        String query; // Defines the search query

        // Determine search strategy based on user choice
        switch (choice) {
            case 1 -> {
                strategy = new CategorySearchStrategy();
                System.out.print("Enter category to search (e.g., Grocery, Electronics, Clothing): ");
                query = scanner.nextLine();
            }
            case 2 -> {
                strategy = new NameSearchStrategy();
                System.out.print("Enter product name to search: ");
                query = scanner.nextLine();
            }
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        // Perform the search using the chosen strategy
        List<Product> results = strategy.search(products, query);

        // Display the search results
        displaySearchResults(results);
    }

    /**
    * Displays the results of a product search.
    * <p>
    * If no products match the search criteria, a message is displayed indicating that no products were found.
    * Otherwise, the details of each matching product are displayed.
    * </p>
    *
    * @param results A list of {@link Product} objects that match the search criteria.
    */
    public static void displaySearchResults(List<Product> results) {
        if (results.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Search results:");
            results.forEach(p -> System.out.println(p.displayInfo()));
        }
    }

    
    /**
    * Manages composite products in the online store system.
    * <p>
    * This method provides a menu for the admin to manage composite products, including creating a new composite,
    * adding or removing individual products from a composite, and displaying composite product details.
    * </p>
    * <p>
    * The admin user is required to access this functionality. If the current user is not an admin, an access
    * denied message is displayed.
    * </p>
    */

    // To manage composite products
    public static void manageCompositeProducts() {
        if (!isAdmin()) {
            System.out.println("Access denied. Only admins can manage composite products.");
            return;
        }

    // The menu options
        System.out.println("Managing Composite Products:");
        System.out.println("1. Create a new composite product");
        System.out.println("2. Add a product to a composite product");
        System.out.println("3. Remove a product from a composite product");
        System.out.println("4. Display composite product details");
        System.out.println("5. Return to the main menu");
        System.out.print("Enter your choice: ");

        int choice = -1;
        boolean validInput = false;

        // Validate user input
        while (!validInput) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 5) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please select between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Return to main menu if chosen
        if (choice == 5) {
            System.out.println("Return to the main menu.");
            return; // Return to main menu
        }

        // Handle choices for managing composites
        switch (choice) {
            case 1 -> createCompositeProduct();
            case 2 -> addProductToComposite();
            case 3 -> removeProductFromComposite();
            case 4 -> displayCompositeDetails();
            default -> System.out.println("Invalid choice.");
        }
    }

    /**
    * Creates a new composite product in the store.
    * <p>
    * This method prompts the admin to enter a name for the new composite product and optionally set a fixed price for it.
    * The admin can also add products to the composite immediately after its creation.
    * </p>
    * <p>
    * If a fixed price is assigned, the price must be greater than 0. If no fixed price is set, the price will be
    * calculated based on the component products added to the composite.
    * </p>
    */
    private static void createCompositeProduct() {
        System.out.print("Enter the name of the new composite product: ");
        String name = scanner.nextLine();

        // Create a new composite product
        ProductComposite composite = new ProductComposite(name);

        // Optionally assign a fixed price to the composite
        System.out.print("Would you like to assign a fixed price to this composite product? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            double price = -1.0;
            boolean validInput = false;

            // Validate fixed price input
            while (!validInput) {
                try {
                    System.out.print("Enter the fixed price for the composite product: ");
                    price = Double.parseDouble(scanner.nextLine());
                    if (price > 0) {
                        validInput = true;
                        composite.setFixedPrice(price); // Assign fixed price
                    } else {
                        System.out.println("Invalid price. Must be greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid price.");
                }
            }
        } else {
            System.out.println("No fixed price assigned. Price will be calculated from component products.");
        }

        // Add the composite product to the store
        SingletonStore.getInstance().addProduct(composite);

        System.out.println("Composite product \"" + name + "\" created successfully.");

        // Optionally add products to the composite immediately
        System.out.print("Would you like to add products to the composite now? (yes/no): ");
        response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            boolean addingProducts = true;

            // Allow the user to add multiple products to the composite
            while (addingProducts) {
                System.out.print("Enter the name of the product to add: ");
                String productName = scanner.nextLine();

                Product product = SingletonStore.getInstance().getProducts().stream()
                        .filter(p -> p.getName().equalsIgnoreCase(productName))
                        .findFirst()
                        .orElse(null);

                if (product != null) {
                    composite.addProduct(product); // Add the product to the composite
                } else {
                    System.out.println("Product not found. Please try again.");
                }

                System.out.print("Would you like to add another product? (yes/no): ");
                addingProducts = scanner.nextLine().trim().toLowerCase().equals("yes");
            }
        }

        System.out.println("Composite product \"" + name + "\" is now ready.");
    }



    /**
    * Adds an existing product to a composite product.
    * <p>
    * This method allows the user to add a product to an existing composite product. It first checks
    * if the composite product exists, then prompts the user for the name of the product to add. If
    * the product exists, it is added to the composite product; otherwise, an error message is displayed.
    * </p>
    */

    // To add a product to a composite product
    private static void addProductToComposite() {
        System.out.print("Enter the name of the composite product: ");
        String compositeName = scanner.nextLine();

        // Find the composite product
        ProductComposite composite = (ProductComposite) SingletonStore.getInstance().getProducts().stream()
                .filter(p -> p instanceof ProductComposite && p.getName().equalsIgnoreCase(compositeName))
                .findFirst()
                .orElse(null);

        if (composite == null) {
            System.out.println("Composite product not found.");
            return;
        }

        System.out.print("Enter the name of the product to add: ");
        String productName = scanner.nextLine();

        // Find the product to add to the composite
        Product product = SingletonStore.getInstance().getProducts().stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);

        if (product == null) {
            System.out.println("Product not found.");
        } else {
            composite.addProduct(product);
            System.out.println("Product added to composite: " + product.getName());
        }
    }

    /**
    * Removes a product from an existing composite product.
    * <p>
    * This method allows the user to remove a product from a composite product. It first checks if the
    * composite product exists and then prompts the user for the name of the product to remove. If the
    * product is part of the composite, it is removed; otherwise, an error message is displayed.
    * </p>
    */

    // To remove a product from a composite product
    private static void removeProductFromComposite() {
        System.out.print("Enter the name of the composite product: ");
        String compositeName = scanner.nextLine();

        // Find the composite product
        ProductComposite composite = (ProductComposite) SingletonStore.getInstance().getProducts().stream()
                .filter(p -> p instanceof ProductComposite && p.getName().equalsIgnoreCase(compositeName))
                .findFirst()
                .orElse(null);

        if (composite == null) {
            System.out.println("Composite product not found.");
            return;
        }

        System.out.print("Enter the name of the product to remove: ");
        String productName = scanner.nextLine();

        // Find the product to remove from the composite
        Product product = composite.getProducts().stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);

        if (product == null) {
            System.out.println("Product not found in the composite.");
        } else {
            composite.removeProduct(product);
            System.out.println("Product removed from composite: " + product.getName());
        }
    }


    /**
    * Displays the details of a composite product, including its components and total price.
    * <p>
    * This method prompts the user to enter the name of a composite product, then retrieves and displays
    * its details, including the names and discounted prices of the individual products that make up the composite.
    * The total discounted price of the composite is also displayed.
    * </p>
    */

    // To display the details of a composite product
    private static void displayCompositeDetails() {
        System.out.print("Enter the name of the composite product: ");
        String compositeName = scanner.nextLine();

        // Retrieve the composite product
        ProductComposite composite = (ProductComposite) SingletonStore.getInstance().getProducts().stream()
                .filter(p -> p instanceof ProductComposite && p.getName().equalsIgnoreCase(compositeName))
                .findFirst()
                .orElse(null);

        if (composite == null) {
            System.out.println("Composite product not found.");
            return;
        }

        // Display composite details
        System.out.println("Composite Product: " + composite.getName());
        System.out.println("Components:");
        composite.getProducts().forEach(p -> System.out.println("- " + p.getName() + ": $" + p.getDiscountedPrice()));
        System.out.println("Total Price: $" + composite.getDiscountedPrice());
    }

    /**
    * Adds a product to the shopping cart.
    * <p>
    * This method allows the user to add a product to their shopping cart. It first prompts the user to log in
    * if they are not already logged in. Then, it displays available products and prompts the user to enter the name
    * of the product they wish to add. The user is also prompted to enter the quantity of the product to add.
    * If the product is found and the quantity is valid, the product is added to the cart.
    * </p>
    * <p>
    * The method performs input validation to ensure the quantity is a positive integer.
    * </p>
    */

    // To add a product to the cart
    public static void addProductToYourCart() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }

        // Display available products
        displayProducts();

        // Prompt user for product name
        System.out.print("\nEnter the name of the product you want to add to your cart: ");
        String productName = scanner.nextLine();

        // Find the selected product
        Product selectedProduct = SingletonStore.getInstance().getProducts().stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);

        // If product is found, prompt for quantity
        if (selectedProduct != null) {
            int quantity = -1;
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Enter the quantity: ");
                    quantity = Integer.parseInt(scanner.nextLine());
                    if (quantity > 0) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid quantity. Must be greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a positive number.");
                }
            }

            // Add product and quantity to the cart
            cart.addProduct(selectedProduct, quantity);
            System.out.println(quantity + " " + selectedProduct.getName() + "(s) added to your cart.");
        } else {
            System.out.println("Product not found.");
        }
    }

    
    /**
    * Displays all the products available in the store, grouped by their categories.
    * <p>
    * This method retrieves all products from the {@link SingletonStore}, and groups them by their
    * respective categories. For each category, it displays the category name, a table of product details,
    * including the product name, original price, and discounted price (if available). 
    * If no products are available, a message is displayed informing the user.
    * </p>
    */
    public static void displayProducts() {
        // Retrieve all products from the SingletonStore
        List<Product> products = SingletonStore.getInstance().getProducts();
    
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            // Group products by their category
            Map<String, List<Product>> groupedProducts = products.stream()
                    .collect(Collectors.groupingBy(Product::getProductCategory));

            // Iterate over each category and display its products
            for (String category : groupedProducts.keySet()) {
                System.out.printf("\nCategory: %s\n", category); // Display the category name
                System.out.println("=".repeat(100)); // Separator for each category
            
                // Display the table header
                System.out.printf("%-40s %-20s %-20s%n", "Product Name", "Original Price", "Discounted Price");
                System.out.println("=".repeat(100)); // Add a separator for better readability

                // Display products within this category
                for (Product product : groupedProducts.get(category)) {
                    String discountedPrice = product.getDiscountedPrice() < product.getPrice() 
                            ? String.format("$%.2f", product.getDiscountedPrice()) 
                            : "no discount";
                    System.out.printf("%-40s $%-19.2f %-20s%n",
                        product.getName(),
                        product.getPrice(),
                        discountedPrice
                    );
                }
            }
        }
    }

    /**
    * Removes a specified product from the user's shopping cart.
    * <p>
    * This method prompts the user to enter the name of the product they wish to remove from their cart.
    * If the product is found in the cart, it is removed; otherwise, a message is displayed indicating
    * the product was not found in the cart.
    * </p>
    */
    public static void removeProductFromYourCart() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }

        // Prompt user to enter the product name to remove
        System.out.println("Enter the name of the product to remove from your cart:");
        String productName = scanner.nextLine();

        // Find the product in the cart
        Product productToRemove = cart.getProducts().stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);

        if (productToRemove != null) {
            cart.removeProduct(productToRemove); // Remove the product from the cart
        } else {
            System.out.println("Product not found in your cart.");
        }
    }

    /**
     * Views the current user's shopping cart.
     * <p>
     * This method checks if the user is logged in. If the user is logged in, it displays the items in the
     * cart, including the names, quantities, and prices of the products. If the user is not logged in, 
     * it prompts the user to log in first.
     * </p>
     */
    public static void viewCart() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        cart.showCartItems(); // Display cart items
    }

    /**
     * Processes the payment for the items in the user's shopping cart.
     * <p>
     * This method checks if the user is logged in. If the user is logged in, it displays the total amount
     * for the cart items and prompts the user to select a payment method. Available methods are:
     * Credit Card, PayPal, and Debit Card. If the payment is successful, an order is created and added to
     * the order history, and the cart is cleared. If the payment fails, an error message is displayed.
     * </p>
     *
     * @throws IllegalArgumentException If the user selects an invalid payment method.
     */
    public static void proceedToPayment() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }

        // Calculate the total for the cart items
        double total = cart.calculateTotal();
        System.out.printf("Your total is: $%.2f%n", total);

        // Prompt the user to select a payment method
        System.out.println("Select a payment method: 1. Credit Card, 2. PayPal, 3. Debit Card");
        String paymentChoice = scanner.nextLine().trim();

        // Map the user choice to a payment method
        String paymentMethod = switch (paymentChoice) {
            case "1" -> "Credit Card";
            case "2" -> "PayPal";
            case "3" -> "Debit Card";
            default -> null;
        };

        if (paymentMethod == null) {
            System.out.println("Invalid payment method. Please try again.");
            return;
        }

        // Process the payment
        Payment payment = new Payment(paymentMethod);
        if (payment.processPayment(total)) {
            System.out.println("Payment successful!");

            // Generate a unique order ID
            String orderId = "ORD-" + (int) (Math.random() * 1_000_000);

            // Create an order and add it to the order history
            Order order = new Order(orderId, cart.getProducts(), cart.getQuantities(), total);
            orderHistory.addOrder(order);

            // Clear the cart
            cart.clear();
            System.out.println("Your cart has been cleared.");
        } else {
            System.out.println("Payment failed!");
        }
    }



    /**
     * Allows the user to review a product.
     * <p>
     * This method prompts the user to log in, display available products, and then choose a product to review.
     * The user is asked to provide a review text and a rating (from 1 to 5). The review is then added to the
     * selected product. If the product is not found or the rating is invalid, appropriate error messages are displayed.
     * </p>
     * <p>
     * The review is added to the product if the input is valid, and a success message is shown. Otherwise, the user
     * is informed of the invalid input.
     * </p>
     */
    public static void reviewProduct() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }

        // Display available products
        System.out.println("Available products:");
        SingletonStore.getInstance().displayProducts();

        System.out.print("Enter the name of the product to review: ");
        String productName = scanner.nextLine();

        // Search for the product by name (case-insensitive)
        Product product = SingletonStore.getInstance().getProducts().stream()
                .filter(p -> p.getName().toLowerCase().contains(productName.toLowerCase()))
                .findFirst()
                .orElse(null);

        if (product != null) {
            // Prompt for review text and rating
            System.out.print("Enter your review: ");
            String reviewText = scanner.nextLine();
            
            System.out.print("Enter your rating (1-5): ");
            int rating = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Validate the rating
            if (rating >= 1 && rating <= 5) {
                ProductReview review = new ProductReview("User", reviewText, rating);
                product.addReview(review); // Add the review to the product

                System.out.println("Review added successfully.");
            } else {
                System.out.println("Invalid rating. Must be between 1 and 5.");
            }
        } else {
            System.out.println("Product not found. Please ensure the name matches exactly or try partial name.");
        }
    }

    /**
     * Displays all product reviews.
     * <p>
     * This method prompts the user to log in and then displays all the reviews for the products available in the store.
     * If no products are available, a message indicating so is shown. For each product, its reviews are displayed.
     * </p>
     * <p>
     * If the user is not logged in, they are asked to log in first before viewing the reviews.
     * </p>
     */
    public static void reviews() {
        if (!isLoggedIn) {
            System.out.println("Please login first to view reviews.");
            return;
        }

        // Display product reviews
        System.out.println("Product Reviews:");
        List<Product> products = SingletonStore.getInstance().getProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            // Display reviews for each product
            for (Product product : products) {
                product.displayReviews(); // Display reviews for the product
            }
        }
    }


    /**
     * Allows the admin to add a new product to the store.
     * <p>
     * This method first checks if the user is logged in and has admin privileges. If the user is not an admin,
     * access is denied. If the user is an admin, they are presented with a menu to choose the type of product
     * to add (Grocery, Electronics, or Clothing). The user is then prompted to enter the product name and price.
     * Once the product details are entered and validated, the product is created using the corresponding product factory
     * and added to the store.
     * </p>
     * <p>
     * The product is added to the store if the price is valid and greater than 0. If the user selects option 4, 
     * they are returned to the main menu.
     * </p>
     */
    public static void addNewProduct() {
        // Ensure the user is logged in and is an admin
        if (!isAdmin()) {
            System.out.println("Access denied. Only admins can add new products.");
            return;
        }

        // Menu for adding a product
        System.out.println("Choose a product type to add:");
        System.out.println("1. Grocery");
        System.out.println("2. Electronics");
        System.out.println("3. Clothing");
        System.out.println("4. Return to the main menu"); // Option to return to the main menu

        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 4) {
                    validInput = true; // Valid choice
                } else {
                    System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1-4).");
            }
        }

        // Return to main menu if user selects option 4
        if (choice == 4) {
            System.out.println("Return to the main menu.");
            return;
        }

        // Determine the product type
        ProductFactory factory = switch (choice) {
            case 1 -> new GroceryFactory();
            case 2 -> new ElectronicsFactory();
            case 3 -> new ClothingFactory();
            default -> null; // Should not occur due to validation
        };

        // Input product details
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        double price = -1.0;
        validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter product price: ");
                price = Double.parseDouble(scanner.nextLine());
                if (price > 0) {
                    validInput = true;
                } else {
                    System.out.println("Invalid price. Must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid price.");
            }
        }

        // Create and add the new product
        Product newProduct = factory.createProduct(name, price);
        SingletonStore.getInstance().addProduct(newProduct);
        System.out.println("Product added successfully: " + newProduct.displayInfo());
    }

    
    /**
     * Allows the admin to apply a discount to a specific product.
     * <p>
     * This method first checks if the user is logged in and has admin privileges. If the user is not an admin,
     * access is denied. If the user is an admin, they can select a product from the store to apply a discount.
     * The user is prompted to enter a discount percentage (between 0 and 100). Once a valid discount percentage
     * is provided, the discount is applied to the selected product, and a confirmation message is displayed.
     * </p>
     * <p>
     * If the product is not found, an error message is displayed asking the user to check the product name.
     * If the user enters an invalid discount value (outside the range 0-100), they are prompted to try again.
     * </p>
     */
    public static void applyDiscountToProduct() {
        // Ensure the user is logged in and is an admin
        if (!isAdmin()) {
            System.out.println("Access denied. Only admins can apply discounts.");
            return;
        }

        // Display products
        displayProducts();

        System.out.print("Enter the name of the product to apply a discount: ");
        String productName = scanner.nextLine();

        // Find the product
        Product product = SingletonStore.getInstance().getProducts().stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);

        if (product != null) {
            double discount = -1.0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Enter the discount percentage (0-100): ");
                    discount = Double.parseDouble(scanner.nextLine());
                    if (discount >= 0 && discount <= 100) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid percentage. Please enter a value between 0 and 100.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                }
            }

            // Apply the discount
            product.applyDiscount(discount);
            System.out.printf("A discount of %.2f%% has been applied to %s.%n", discount, product.getName());
        } else {
            System.out.println("Product not found. Please check the name and try again.");
        }
    }



    /**
     * Signs out the current user.
     * <p>
     * This method checks if the user is logged in. If the user is logged in, it logs the user out by setting
     * the {@code isLoggedIn} flag to {@code false} and clearing the {@code currentUser}. A success message is
     * displayed to indicate that the user has successfully signed out. If the user is not logged in, an error
     * message is displayed informing the user that they are not logged in.
     * </p>
     */
    public static void signOut() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
        } else {
            isLoggedIn = false;
            currentUser = null; // Clear the current user
            System.out.println("You have successfully signed out.");
        }
    }
}
