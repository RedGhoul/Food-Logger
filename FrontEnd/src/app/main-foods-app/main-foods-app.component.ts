import { Component, OnInit } from '@angular/core';
import {FoodService} from "../services/food.service";
import {IFood} from "../models/Food";
import {BsModalRef, BsModalService, ModalOptions} from "ngx-bootstrap/modal";
import {CreateEditFoodModalComponent} from "../create-edit-food-modal-component/create-edit-food-modal.component";
import {PageChangedEvent} from "ngx-bootstrap/pagination";

@Component({
  selector: 'app-main-foods-app',
  templateUrl: './main-foods-app.component.html',
  styleUrls: ['./main-foods-app.component.css']
})
export class MainFoodsAppComponent implements OnInit {
  shouldShowAlert = false;
  isDismissible = true;
  foodCount = 0;
  allFoods: IFood[] = [];
  bsModalFoodCreateRef?: BsModalRef
  constructor(public foodService: FoodService, private modalService: BsModalService) { }

  ngOnInit(): void {
    this.foodService.getFoodsPaged(1,10).subscribe(response => {
      if(response.ok && response.body){
        this.allFoods = response.body;
      }
    }, error => {
      this.shouldShowAlert = true;
    });
    this.foodService.getAllFoods().subscribe(response => {
      if(response.ok && response.body){
        this.foodCount = response.body?.length;
      }
    })
  }

  openAddNewFoodModal(){
    const initialState: ModalOptions = {
      id: 4,
      initialState: {
        id: 4,
        title: 'Create Food'
      }
    };
    this.bsModalFoodCreateRef = this.modalService.show(CreateEditFoodModalComponent, initialState);
    this.bsModalFoodCreateRef.content.closeBtnName = 'Close';
  }

  openEditFoodModal(foodId: number){
    const initialState: ModalOptions = {
      id: 3,
      initialState: {
        id: 3,
        currentFood: this.allFoods[foodId-1],
        title: 'Edit Food'
      }
    };
    this.bsModalFoodCreateRef = this.modalService.show(CreateEditFoodModalComponent, initialState);
    this.bsModalFoodCreateRef.content.closeBtnName = 'Close';
  }

  pageChanged(event: PageChangedEvent): void {
    console.log("startItem" + event.page)
    this.foodService.getFoodsPaged(event.page,10).subscribe(response => {
      if(response.ok && response.body){
        this.allFoods = response.body;
      }
    }, error => {
      this.shouldShowAlert = true;
    });
  }

}
