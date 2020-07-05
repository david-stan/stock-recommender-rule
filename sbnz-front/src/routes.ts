import { StockListComponent } from './app/components/stock-list/stock-list.component';


export const appRoutes = [
  { path: 'home', component: StockListComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];
