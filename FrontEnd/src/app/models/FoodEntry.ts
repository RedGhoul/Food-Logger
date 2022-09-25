import { IFood } from "./Food";
import { FoodEntryType } from "./FoodEntryType.enum";

export interface IFoodEntry {
  id: number;
  food: IFood;
  userId: number;
  entryType: FoodEntryType;
}
