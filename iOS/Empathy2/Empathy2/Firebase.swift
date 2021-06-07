//
//  Firebase.swift
//  Empathy2
//
//  Created by Vlad on 14/04/2021.
//

import Foundation
import UIKit
import Firebase

struct Firebase{
    let fire : FirebaseApp
     
    func connect(){
        FirebaseApp.configure()
        let db = Firestore.firestore()
        
        print(db)
       
    }
}


