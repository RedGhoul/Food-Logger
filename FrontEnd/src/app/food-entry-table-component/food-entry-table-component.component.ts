import {Component, Input, OnInit} from '@angular/core';
import {IFoodEntry} from '../models/FoodEntry';
import {FoodEntryService} from "../services/food-entry.service";
import {FoodEntryType} from "../models/FoodEntryType.enum";

@Component({
  selector: 'app-food-entry-table-component',
  templateUrl: './food-entry-table-component.component.html',
  styleUrls: ['./food-entry-table-component.component.css']
})
export class FoodEntryTableComponent implements OnInit {
  @Input() foodEntries: IFoodEntry[] = [];
  @Input() tableName: string = "";
  constructor(public foodEntryService: FoodEntryService) { }

  ngOnInit(): void {
  }

  removeFood(entryId: number){
    let type = Object.values(FoodEntryType).find(x => x == this.tableName);
    if(type){
      this.foodEntryService.removeFoodEntryById(entryId, type);
    }

    console.log("I need to remove entry " + entryId);
  }

}
