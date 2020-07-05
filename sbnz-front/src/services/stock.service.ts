import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class StockService {
  constructor(private http: HttpClient) {

  }

  getStocks(data) {
    return this.http.post('http://localhost:8080/stocks', data);
  }
}
