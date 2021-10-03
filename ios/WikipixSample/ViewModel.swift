//
//  ViewModel.swift
//  WikipixSample
//
//  Created by Abdul Chathil on 09/08/21.
//

import Foundation
import wikipix

class ViewModel: ObservableObject {
  @Published var url = ""
  
  private let useCase: WikipixUseCase
  init(useCase: WikipixUseCase) {
    self.useCase = useCase
  }
  
  func startObserving(title: String) {
    useCase.observeUrl(title: title, completionHandler: { response in
      if let success = response as? ResourceSuccess<NSString> {
        let string = (success.data as? String) ?? "https://via.placeholder.com/300"
        self.url = string
      } else if response is ResourceLoading<NSString> {
        print("Loading...")
      } else if let error = response as? ResourceError<NSString> {
        print("Error: \(error.message)")
      }
    })
  }
  
  func onDestroy() {
    useCase.onDestroy()
  }

}
