import React, { useReducer, createContext, useContext } from "react";

const products = [
  { id: "p1", name: "Apple", price: 100 },
  { id: "p2", name: "Mango", price: 100 },
  { id: "p3", name: "Banana", price: 50 },
];

const CartContext = createContext();


function cartReducer(state, action) {
  switch (action.type) {
    case "ADD":
      const existingItem = state.cart.find(item => item.id === action.payload.id);
      let updatedCart;

      if (existingItem) {
        updatedCart = state.cart.map(item =>
          item.id === action.payload.id
            ? { ...item, count: item.count + 1 }
            : item
        );
      } else {
        updatedCart = [...state.cart, { ...action.payload, count: 1 }];
      }

      return {
        cart: updatedCart,
        totalCost: updatedCart.reduce((total, item) => total + item.price * item.count, 0),
      };

    case "REMOVE":
      const filteredCart = state.cart.filter(item => item.id !== action.payload.id);
      return {
        cart: filteredCart,
        totalCost: filteredCart.reduce((total, item) => total + item.price * item.count, 0),
      };

    default:
      return state;
  }
}

function CartProvider({ children }) {
  const initialState = { cart: [], totalCost: 0 };
  const [state, dispatch] = useReducer(cartReducer, initialState);

  return (
    <CartContext.Provider value={{ state, dispatch }}>
      {children}
    </CartContext.Provider>
  );
}

function ProductList() {
  const { dispatch } = useContext(CartContext);

  return (
    <>
      <h2>Products</h2>
      {products.map(product => (
        <div key={product.id}>
          <span>{product.name} - ₹{product.price} </span>
          <button onClick={() => dispatch({ type: "ADD", payload: product })}>
            Add
          </button>
        </div>
      ))}
    </>
  );
}

function Cart() {
  const { state, dispatch } = useContext(CartContext);

  return (
    <>
      <h2>Cart</h2>
      {state.cart.length === 0 && <p>Cart is empty.</p>}
      {state.cart.map(item => (
        <div key={item.id}>
          {item.name} {item.count} * ₹{item.price} = ₹{item.count * item.price}
          <button
           
            onClick={() => dispatch({ type: "REMOVE", payload: item })}
          >
            Remove
          </button>
        </div>
      ))}
      <h3>Total Cost: ₹{state.totalCost}</h3>
    </>
  );
}

function App() {
  return (
    <CartProvider>
      <h1>Shopping Cart</h1>
      <ProductList />
      <Cart />
    </CartProvider>
  );
}

export default App;
