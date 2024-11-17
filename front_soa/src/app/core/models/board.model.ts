import { List } from "./list.model";

// board.model.ts
export interface Board {
    id?: number;
    name?: string;
    lists?: List[];
    users?: List[];
}