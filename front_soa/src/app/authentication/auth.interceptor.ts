import { HttpInterceptorFn } from "@angular/common/http";
import { catchError, throwError } from "rxjs";

export const AuthInterceptor: HttpInterceptorFn = (req,next)=> {
    const token=localStorage.getItem('authToken');
    if(token){
        const cloned = req.clone({
            headers: req.headers.set('Authorization',`Bearer ${token}`)
        });
        return next(cloned).pipe(catchError(err=>{
            if(err.status==401){
                //token expirado o no es valido
                localStorage.removeItem('authToken');
                window.location.href='/login';
            }
            return throwError(err);
        }));
    }
    return next(req);
}