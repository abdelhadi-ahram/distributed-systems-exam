import ListTransfers from "./_components/list-transfers";


async function fetchTransfers() {
  try {
    const response = await fetch('http://localhost:8080/TRANSFER-SERVICE/api/transfers');

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
  const transfers = await fetchTransfers()

  return (
    <div className="w-full container lg:px-48 md:mt-6">
      <h2 className="text-2xl font-semibold">Transfers</h2>
      <p className="text-sm text-muted-foreground mt-2">Here is a ist of transfers made by customers</p>

      <div className="mt-4 space-y-2">
        {transfers && (
          <ListTransfers transfers={transfers} />
        )}
      </div>
    </div>
  )
}