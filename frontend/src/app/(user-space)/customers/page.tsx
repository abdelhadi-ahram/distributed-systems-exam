import ListCustomers from "./_components/list-customers";


async function fetchCustomers() {
  try {
    const response = await fetch('http://localhost:8080/BENEFICIARY-SERVICE/api/beneficiaries');

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error fetching beneficiaries:', error);
    return null;
  }
}

export default async function AppsPage() {
  const customers = await fetchCustomers()

  return (
    <div className="w-full container lg:px-48 md:mt-6">
      <h2 className="text-2xl font-semibold">Customers</h2>
      <p className="text-sm text-muted-foreground mt-2">Here is a ist of Customers, you can delete or update them, or create new</p>

      <div className="mt-4 space-y-2">
        <ListCustomers customers={customers} />
      </div>
    </div>
  )
}