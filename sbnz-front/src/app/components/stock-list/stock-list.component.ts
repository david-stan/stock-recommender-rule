import { Component, OnInit } from '@angular/core';
import { StockService } from 'src/services/stock.service';
import { ActivatedRoute } from '@angular/router';
import { IStock } from 'src/models/Stock';

@Component({
  selector: 'app-stock-list',
  templateUrl: './stock-list.component.html',
  styleUrls: ['./stock-list.component.css']
})
export class StockListComponent implements OnInit {

  topStocks: IStock[];

  risk = false;
  experience = false;
  economy = false;
  bank = false;
  funds = false;
  technology = false;
  medical = false;

  constructor(private stockService: StockService,
              private route: ActivatedRoute) {

  }

  ngOnInit() {
  }

  onClickSubmit() {
    const data = {
      risk: this.risk,
      funds: this.funds,
      experience: this.experience,
      economy: this.economy,
      bank: this.bank,
      technology: this.technology,
      medical: this.medical
    };
    console.log(data);
    this.stockService.getStocks(data).subscribe(
      (retval: any) => {
        console.log(retval);
        this.topStocks = retval.riskFreeStocks;
        console.log(this.topStocks);
      }
    );
  }
}
