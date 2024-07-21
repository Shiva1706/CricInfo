import { Routes } from '@angular/router';
import path from 'path';
import { HomeComponent } from './pages/home/home.component';
import { HistoryComponent } from './pages/history/history.component';
import { LiveComponent } from './pages/live/live.component';
import { PointTableComponent } from './pages/point-table/point-table.component';

export const routes: Routes = [
    {path: "", redirectTo: "/home", pathMatch:'full'},
    {path: "home", component: HomeComponent, title: "Home | CricInfo"},
    {path: "history", component: HistoryComponent, title: "Match-History | CricInfo"},
    {path: "live", component: LiveComponent, title: "Live-Matches | CricInfo" },
    {path: "point-table", component: PointTableComponent, title: "Point-Table | CricInfo"}
];
