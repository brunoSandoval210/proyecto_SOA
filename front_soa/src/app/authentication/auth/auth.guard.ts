import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate{

    constructor(private router: Router){}

    canActivate(): boolean{
        const token=localStorage.getItem('authToken');
        if(!token){
            console.log('NO se contro token,devuelta al login');
            this.router.navigate(['/']);
            return false;
        }
        console.log("Se encontro token");
        return true;
    }

}