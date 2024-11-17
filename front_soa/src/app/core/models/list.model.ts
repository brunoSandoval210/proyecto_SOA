import { Task } from "./task.model";

// list.model.ts
export interface List {
    id?: number;
    name?: string;
    tasks: Task[];
}