import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { BsModalService } from 'ngx-bootstrap/modal';
import { IFood } from '../models/Food';

@Component({
  selector: 'app-create-food-modal',
  templateUrl: './create-edit-food-modal.component.html',
  styleUrls: ['./create-edit-food-modal.component.css']
})
export class CreateEditFoodModalComponent implements OnInit {
  id = 0;
  title = "Create New Food";
  closeBtnName = 'Close';
  currentFood: IFood | undefined;
  foodForm = this.formBuilder.group({
    name: ['', [Validators.required, Validators.minLength(1)]],
    proteins: [0, [Validators.required, Validators.maxLength(15)]],
    calories: [0, [Validators.required, Validators.maxLength(15)]],
    carbohydrates: [0, [Validators.required, Validators.maxLength(15)]],
    fat: [0, [Validators.required, Validators.maxLength(15)]],
    sodium: [0, [Validators.required, Validators.maxLength(15)]],
  });
  constructor(
    protected http: HttpClient,
    private modalService: BsModalService,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    if(this.currentFood){
      this.foodForm.controls['name'].setValue(this.currentFood.name);
      this.foodForm.controls['proteins'].setValue(this.currentFood.proteins);
      this.foodForm.controls['calories'].setValue(this.currentFood.calories);
      this.foodForm.controls['carbohydrates'].setValue(this.currentFood.carbohydrates);
      this.foodForm.controls['fat'].setValue(this.currentFood.fat);
      this.foodForm.controls['sodium'].setValue(this.currentFood.sodium);
    }
  }

  onSubmit() {
    console.log(this.foodForm.value);
    if (this.foodForm.valid) {
      this.http.post<IFood[]>(`http://localhost:8080/api/foods`,
        this.foodForm.getRawValue() as IFood,
        { observe: 'response' }).subscribe(data => {
          console.log(data);
          this.closeModal();
        });
    }
  }

  closeModal(): void {
    this.modalService.hide(this.id);
  }
}
