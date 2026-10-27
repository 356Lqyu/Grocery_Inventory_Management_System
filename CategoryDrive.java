/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;

public class CategoryDrive {

    private static Category[] addCategoryList;
    private static Category[] viewCategoryList;
    private static Category[] modifyCategoryList;

    private static final int MAX_CATEGORY = 100;
    private static boolean validChoice;

    //Scanner
    private static final Scanner scan = new Scanner(System.in);

    // Category Menu
    public static void categoryMenu() {

        Home.clearScreen();

        try {
            File myCat = new File("category.txt");
            if (myCat.createNewFile()) {
                System.out.println("\n\n\t\t File created: " + myCat.getName());
            }
        } catch (IOException e) {
            System.out.println("\n\t\t  -- An error occurred! --");
            e.printStackTrace();
        }

        System.out.println("\n\n\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|                         CATEGORY MENU                         |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\t\t|       1. ADD Category                                         |");
        System.out.println("\t\t|       2. VIEW Category                                        |");
        System.out.println("\t\t|       3. UPDATE Category Info                                 |");
        System.out.println("\t\t|       4. Back                                                 |");
        System.out.println("\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        // DO-WHILE loop handles incorrect input selections
        int choice = Home.getValidChoice(4);

        switch (choice) {
            case 1:
                addCategory();
                break;
            case 2:
                viewCategory();
                backToCategoryMenu();
                break;
            case 3:
                updateCategoryInfo();
                break;
            case 4:
                Home.homeMenu();
                break;
        }
    }

    // Method to add category
    public static void addCategory() {

        // Let user enter the number of categories they would like to enter
        int numCat;
        boolean validNumCat;

        Home.clearScreen();

        // Read the category text file to get the last category ID, then increment it (Eg: C1000 -> C1001)
        try (BufferedReader readCategory = new BufferedReader(new FileReader("category.txt"))) {
            String catLastLine = "", line;
            while ((line = readCategory.readLine()) != null) {
                catLastLine = line;
            }
            if (!catLastLine.isEmpty()) {
                //Extract the numeric pasrt of the last category ID , then add with 1 (Eg: C1000 -> C1001)
                String lastCategoryId = catLastLine.split("\\|")[0];
                if (lastCategoryId.startsWith("C") && lastCategoryId.length() > 1) {
                    Category.nextCatCount = Integer.parseInt(lastCategoryId.substring(1)) + 1;
                } else {
                    System.out.println("\n\n\t\t   ** Invalid Category ID format in the file! **");
                }
            }
        } catch (IOException e) {
            System.out.println("\n\n\t\t   ** An error occurred while reading category IDs! **");
            e.printStackTrace();
        }

        // DO-WHILE loop handles invalid number of category entered , if invalid , then user may re-enter valid number of category
        do {
            System.out.print("\n\t\tEnter the number of category to add : ");

            try {
                numCat = scan.nextInt();
                scan.nextLine();

                if (numCat > 0) {
                    validNumCat = true;

                    // Initialize the array with size entered
                    addCategoryList = new Category[numCat];

                    System.out.println("\n\n\t\t===========================================================================");
                    System.out.println("\t\t|                             ADD CATEGORY                                |");
                    System.out.println("\t\t===========================================================================");

                    try {

                        //Open file in append
                        FileWriter writeCategory = new FileWriter("category.txt", true);

                        for (int a = 0; a < addCategoryList.length; a++) {

                            // The category id will be generated by using "C" + categoryCount (eg : C1001)
                            String categoryId = "C" + Category.getNextCatCount();
                            System.out.println("\t\t|                                                                         |");
                            System.out.printf("\t\t|  Category %d :                                                           |\n", a + 1);
                            System.out.println("\t\t| ----------------                                                        |");
                            System.out.printf("\t\t|   1. Category ID                               : %s\n", categoryId);
                            System.out.println("\t\t                                                                          |");

                            System.out.print("\t\t|   2. Enter Name                                : ");
                            String categoryName = scan.nextLine();
                            System.out.println("\t\t                                                                          |");
                            System.out.println("\t\t|                                                                         |");
                            System.out.println("\t\t---------------------------------------------------------------------------");

                            // Create a new Category object and add it to array
                            addCategoryList[a] = new Category(categoryName);

                            // Write to the file
                            writeCategory.write(addCategoryList[a].toString() + "\n");
                        }

                        writeCategory.close();
                        System.out.println("\n\n\t\t   ** Added successfully! **");

                        viewCategory();

                    } catch (IOException e) {
                        System.out.println("\n\n\t\t   ** An error occurred!!! **");
                        e.printStackTrace();
                    }

                    // Let user choose to continue adding category or back to category menu
                    continueAddingCat();
                } else {
                    System.out.println("\t\t       ---  Invalid input!!! Please enter number of category (>0)-- \n");
                    validNumCat = false;
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("\t\t       ---  Invalid input!!! Please enter number of category (>0)-- ");
                validNumCat = false;
            }
        } while (!validNumCat);

    }

    //Method to continue add category
    public static void continueAddingCat() {

        System.out.println("\n\t\t\t\t-----------------------------------");
        System.out.println("\t\t\t\t| Choice                          |");
        System.out.println("\t\t\t\t-----------------------------------");
        System.out.println("\t\t\t\t| 1. Continue Adding Category     |");
        System.out.println("\t\t\t\t| 2. Back to Category Menu        |");
        System.out.println("\t\t\t\t-----------------------------------");

        int choice = Home.getValidChoice(2);

        switch (choice) {
            case 1:
                addCategory();
                break;
            case 2:
                categoryMenu();
                break;
        }
    }

    // Method to view category list
    public static void viewCategory() {

        Home.clearScreen();
        File catList = new File("category.txt");

        // Initialize the array for the display category list
        viewCategoryList = new Category[MAX_CATEGORY];

        System.out.println("\n\n\t\t\t\t\t\t CATEGORY LIST");
        System.out.println("\t\t\t\t\t\t---------------");
        System.out.println("\t\t  _________________________________________________________________________");
        System.out.println("\t\t  |             ID             |                   NAME                   |");
        System.out.println("\t\t  -------------------------------------------------------------------------");

        // Read the file to show all category details
        try (Scanner fileScanner = new Scanner(catList)) {
            int g = 0;

            while (fileScanner.hasNextLine()) {
                // Read the Next Line(remove any leading and trailing whitespace characters)
                String categoryData = fileScanner.nextLine().trim();

                // Skip empty lines
                if (categoryData.isEmpty()) {
                    continue;
                }

                // Split the category details by the delimiter "|"
                String[] categoryDetails = categoryData.split("\\|");

                // Ensure the line has exactly 2 elements
                if (categoryDetails.length == 2) {
                    String categoryId = categoryDetails[0];
                    String categoryName = categoryDetails[1];

                    // Create a new Category object using the categoryName
                    viewCategoryList[g] = new Category(categoryName);
                    viewCategoryList[g].setCatId(categoryId);

                    // Print the category information
                    System.out.printf("\t\t  |            %-16s|                  %-24s|\n", categoryId, categoryName);
                    g++;
                } else {
                    System.out.println("\n\t\t  -- Error!!! Category data is missing or corrupted in the file! --");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }
        System.out.println("\t\t  -------------------------------------------------------------------------");
    }

    // Method to let user choose to back category menu when view category list
    public static void backToCategoryMenu() {

        char choiceY;
        System.out.println("\n\n\t\t  Back to Category Menu?(Y)");

        do {
            System.out.print("\t\t  : ");

            try {
                choiceY = scan.next().toUpperCase().charAt(0);

                if (choiceY == 'Y') {
                    validChoice = true;
                    scan.nextLine();
                    Home.clearScreen();
                    categoryMenu();
                } else {
                    validChoice = false;
                    System.out.print("\t\t\t        ---  Invalid choice!!! Please enter (Y)... \n\n");
                }
            } catch (InputMismatchException e) {
                // Clear the invalid input
                scan.next(); // consume the invalid input
                validChoice = false;
                System.out.print("\t\t\t        ---  Invalid choice!!! Please enter (Y)...\n\n");
            }

        } while (!validChoice);

    }

    // Method to update category info (
    public static void updateCategoryInfo() {

        boolean validCatId = false;

        Home.clearScreen();

        // Read the detail from the text file and populate modifyCategoryList
        loadCategoryData();

        // Display the category list
        viewCategory();

        // Update category by entering category id
        System.out.print("\n\t\tEnter the ID of the category you want to modify (CXXXX) : ");
        // Loop until valid category id is entered
        while (!validCatId) {
            String modifyCId = scan.nextLine();

            // Check for empty input
            if (modifyCId.isEmpty()) {
                System.out.println("\t\t\t     -- Category ID cannot be empty! --");
                System.out.print("\n\t\tRe-enter a valid ID (CXXXX) : ");
                continue; // Prompt for ID again
            }

            // Find category index with the match id
            int categoryIndex = findCategoryIndexById(modifyCId);

            if (categoryIndex != -1) {
                validCatId = true;
                Home.clearScreen();

                // Display the specified category's detail with match id
                displayCategoryDetails(modifyCId, categoryIndex);

                // Make selection whether want to modify the name or back to category menu(category id cannot be modified)
                System.out.println("\n\n\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("\t\t\t|  Selection                                          |");
                System.out.println("\t\t\t- ................................................... -");
                System.out.println("\t\t\t|          1. Modify Category Name                    |");
                System.out.println("\t\t\t|          2. Return Category Menu                    |");
                System.out.println("\t\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - -");

                int choice = Home.getValidChoice(2);

                if (choice == 1) {
                    Home.clearScreen();
                    System.out.println("\n\n\t\t\t Modify Category Name:");
                    System.out.println("\t\t\t-------------------------------");
                    System.out.print("\t\t\t Enter new name : ");
                    String modifyCName = scan.nextLine().trim();

                    // Confirm modification
                    if (Home.confirmModification(modifyCategoryList[categoryIndex].getCatName(), modifyCName, "name")) {
                        modifyCategoryList[categoryIndex].setCatName(modifyCName);

                        saveCategoryToFile(modifyCategoryList);
                    }

                } else if (choice == 2) {
                    categoryMenu();
                }

                System.out.println("\n\n");

                // Show the updated category list
                viewCategory();

                // Make selection for continue modify or back to category menu
                continueModifyCat();

            } else {
                System.out.println("\t\t\t     -- Category ID not found! --");
                System.out.print("\n\t\tRe-enter Valid ID (CXXXX) : ");
            }
        }
    }

    // Method to save category to the text file
    public static void saveCategoryToFile(Category[] categoryList) {
        try (FileWriter writeCat = new FileWriter("category.txt")) {

            for (Category category : categoryList) {
                if (category != null) {
                    writeCat.write(category.toString() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("\n\t\t-- An error occurred while saving! --");
            e.printStackTrace();
        }
    }

    // Method to read the detail from the text file and populate modifyCategoryList
    public static void loadCategoryData() {
        if (modifyCategoryList == null) {
            modifyCategoryList = new Category[MAX_CATEGORY]; // Initialize if null
        }

        try (Scanner fileScanner = new Scanner(new File("category.txt"))) {
            int index = 0;
            while (fileScanner.hasNextLine()) {
                String categoryData = fileScanner.nextLine().trim();
                if (!categoryData.isEmpty()) {
                    String[] categoryDetails = categoryData.split("\\|");
                    if (categoryDetails.length == 2) {
                        if (index >= modifyCategoryList.length) {
                            System.out.println("\n\t\t  -- Error!!! Category list is full! --");
                            break;
                        }
                        modifyCategoryList[index] = new Category(categoryDetails[1]);
                        modifyCategoryList[index].setCatId(categoryDetails[0]);
                        index++;
                    } else {
                        System.out.println("\n\t\t  -- Error!!! Category data is missing or corrupted in the file! --");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\n\t\t ** Can't find the file!!! **");
            e.printStackTrace();
        }
    }

    // Method to find the index of a category by its ID (use to modify category)
    public static int findCategoryIndexById(String id) {
        // Ensure category data is loaded
        if (modifyCategoryList == null) {
            loadCategoryData(); // Load category data if not already loaded
        }

        // Check for null or empty id
        if (id == null || id.isEmpty()) {
            return -1; // Invalid input
        }

        // Loop through the list to find the category
        for (int i = 0; i < modifyCategoryList.length; i++) {
            // Check for null elements in the list and Category id found
            if (modifyCategoryList[i] != null && id.equals(modifyCategoryList[i].getCatId())) {
                return i;
            }
        }

        // Category not found
        return -1;
    }

    // Method to get a Category by its index
    public static Category getCategoryByIndex(int index) {
        // Ensure category data is loaded
        if (modifyCategoryList == null) {
            loadCategoryData(); // Load category data if not already loaded
        }

        // Check if the index is valid
        if (index < 0 || index >= modifyCategoryList.length || modifyCategoryList[index] == null) {
            System.out.println("Invalid index provided.");
            return null; // Invalid input
        }

        // Return the Category at the specified index
        return modifyCategoryList[index];
    }

    // Method to get a Category by its ID
    public static Category getCategoryById(String id) {
        // Ensure category data is loaded
        if (modifyCategoryList == null) {
            loadCategoryData(); // Load category data if not already loaded
        }

        // Check for null or empty id
        if (id == null || id.isEmpty()) {
            System.out.println("\t\t\t    -- Invalid ID provided.");
            return null; // Invalid input
        }

        // Loop through the list to find the category
        for (int i = 0; i < modifyCategoryList.length; i++) {
            // Check for null elements in the list and Category id found
            if (modifyCategoryList[i] != null && id.equals(modifyCategoryList[i].getCatId())) {
                return modifyCategoryList[i]; // Return the found Category
            }
        }

        // Category not found
        System.out.println("\t\t\t    -- Category with ID " + id + " not found.");
        return null;
    }

    // Method to display category details with matched ID
    private static void displayCategoryDetails(String categoryId, int categoryIndex) {
        System.out.printf("\n\n\n\t\t\t\t\tDetails of Category with ID(%s)\n", categoryId);
        System.out.println("\t\t  _________________________________________________________________________");
        System.out.println("\t\t  |             ID             |                   NAME                   |");
        System.out.println("\t\t  -------------------------------------------------------------------------");
        System.out.printf("\t\t  |            %-16s|                  %-24s|\n", modifyCategoryList[categoryIndex].getCatId(), modifyCategoryList[categoryIndex].getCatName());
        System.out.println("\t\t  -------------------------------------------------------------------------");
    }

    // Method for continue modify in modifyCategoryInfo function
    private static void continueModifyCat() {

        System.out.println("\n\t\t\t\t---------------------------------------");
        System.out.println("\t\t\t\t| Choice                              |");
        System.out.println("\t\t\t\t---------------------------------------");
        System.out.println("\t\t\t\t| 1. Continue Modify                  |");
        System.out.println("\t\t\t\t| 2. Back to Category Menu            |");
        System.out.println("\t\t\t\t---------------------------------------");

        int choice = Home.getValidChoice(2);
        if (choice == 1 || choice == 2) {
            validChoice = true;
            switch (choice) {
                case 1:
                    updateCategoryInfo(); // Continue modifying
                    break;
                case 2:
                    categoryMenu(); // Return to category menu
                    break;
            }
        } else {
            validChoice = false;
            System.out.println("\t\t\t\t        ---  Invalid choice!!! Please enter (1-2)... \n");
        }

    }

    // Method to print simple list of categories(is used in Product.addProduct(), so the user can enter it when adding a product) -- In form format
    public static void showCategoryList() {

        File catList = new File("category.txt");

        // Initialize the array for the display category list
        Category[] categoryList = new Category[MAX_CATEGORY];

        System.out.println("\t\t|                             CATEGORY LIST");
        System.out.println("\t\t         .........................................................        |");
        System.out.println("\t\t|        :    Category ID    :                Name               :     ");
        System.out.println("\t\t         :.......................................................:        |");

        // Read the file to show all category details
        try (Scanner fileScanner = new Scanner(catList)) {
            int g = 0;

            while (fileScanner.hasNextLine()) {
                // Read the Next Line(remove any leading and trailing whitespace characters)
                String categoryData = fileScanner.nextLine().trim();

                // Skip empty lines
                if (categoryData.isEmpty()) {
                    continue;
                }

                // Split the category details by the delimiter "|"
                String[] categoryDetails = categoryData.split("\\|");

                // Ensure the line has exactly 2 elements
                if (categoryDetails.length == 2) {
                    String categoryId = categoryDetails[0];
                    String categoryName = categoryDetails[1];

                    // Create a new Category object using the categoryName
                    categoryList[g] = new Category(categoryName);
                    categoryList[g].setCatId(categoryId);

                    // Print the category information using simpleCategoryList()
                    System.out.print(simpleCategoryList(categoryList[g]));
                    g++;
                } else {
                    System.out.println("\n\t\t  -- Error!!! Category data is missing or corrupted in the file! --");
                }

            }
            System.out.println("\t\t|        :.......................................................:        |");

        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }

    }

    // Method to print simple list of categories(is used in Product.updateProduct(), so the user can enter it when adding a product id) - in table format
    public static void showCatTable() {

        File catList = new File("category.txt");

        // Initialize the array for the display category list
        Category[] categoryList = new Category[MAX_CATEGORY];

        System.out.println("\n\n\t\t\t                CATEGORY LIST");
        System.out.println("\t\t\t      ....................................................");
        System.out.println("\t\t\t      :    Category ID    :             Name             :");
        System.out.println("\t\t\t      :..................................................:");

        // Read the file to show all category details
        try (Scanner fileScanner = new Scanner(catList)) {
            int g = 0;

            while (fileScanner.hasNextLine()) {
                // Read the Next Line(remove any leading and trailing whitespace characters)
                String categoryData = fileScanner.nextLine().trim();

                // Skip empty lines
                if (categoryData.isEmpty()) {
                    continue;
                }

                // Split the category details by the delimiter "|"
                String[] categoryDetails = categoryData.split("\\|");

                // Ensure the line has exactly 2 elements
                if (categoryDetails.length == 2) {
                    String categoryId = categoryDetails[0];
                    String categoryName = categoryDetails[1];

                    // Create a new Category object using the categoryName
                    categoryList[g] = new Category(categoryName);
                    categoryList[g].setCatId(categoryId);

                    // Print the category information
                    System.out.printf("\t\t\t      :        %-10s :           %-18s :\n", categoryList[g].getCatId(), categoryList[g].getCatName());
                    g++;
                } else {
                    System.out.println("\n\t\t  -- Error!!! Category data is missing or corrupted in the file! --");
                }

            }
            System.out.println("\t\t\t      :..................................................:");
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file!!!");
            e.printStackTrace();
        }

    }

    //Use to print simple list of categories(that is used in Product.addProduct(), so the user can see the existing category to enter it)
    public static String simpleCategoryList(Category category) {
        // Define column widths
        int idWidth = 14;
        int nameWidth = 25;

        // Format the string to align columns
        return String.format("\t\t|        :     %-" + idWidth + "s:          %-" + nameWidth + "s:        |\n", category.getCatId(), category.getCatName());
    }

}
