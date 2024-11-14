import { Task } from "./task.model";

// list.model.ts
export interface List {
    id?: string;
    name?: string;
    tasks: Task[];
  }