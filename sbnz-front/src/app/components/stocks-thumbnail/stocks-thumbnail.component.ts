import { Component, Input } from '@angular/core';
import { IStock } from 'src/models/Stock';

@Component({
  selector: 'app-stocks-thumbnail',
  templateUrl: './stocks-thumbnail.component.html',
  styleUrls: ['./stocks-thumbnail.component.css']
})
export class StocksThumbnailComponent {
  @Input() stock: IStock;

  constructor() {
  }

}
