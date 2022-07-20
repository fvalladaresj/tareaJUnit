package tareaJUnit;

public class Recipe {
	String recipeName;
	int recipePrice;
	int recipeCoffee;
	int recipeChocolate;
	int recipeMilk;
	int recipeSugar;
	
	 public Recipe(String name, int price, int coffee, int chocolate, int milk, int sugar) {
		 this.recipeName = name;
		 this.recipePrice = price;
		 this.recipeCoffee = coffee;
		 this.recipeChocolate = chocolate;
		 this.recipeMilk = milk;
		 this.recipeSugar = sugar;
	  };
	  
	  public String getName() {
		  return recipeName;
	  }
	  
	  public int getPrice() {
		  return recipePrice;
	  }
	  
	  public int[] getIngredients() {
		  int[] ingredients = {0,0,0,0};
		  ingredients[0] = recipeCoffee;
		  ingredients[1] = recipeChocolate;
		  ingredients[2] = recipeMilk;
		  ingredients[3] = recipeSugar;
		  return ingredients;
	  }
}
